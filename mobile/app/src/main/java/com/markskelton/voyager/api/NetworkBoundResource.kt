package com.markskelton.voyager.api

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.markskelton.voyager.AppExecutors

abstract class NetworkBoundResource<T>
@MainThread constructor(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<T>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<T>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<T>) {
        val apiResponse = createCall()

        // We re-attach dbSource as a new source, it will dispatch its latest
        // value quickly.
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        saveCallResult(response.body)

                        appExecutors.mainThread().execute {
                            // We specially request a new live data, otherwise
                            // we will get immediately last cached value, which
                            // may not be updated with latest results received
                            // from network.
                            result.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    // Reload from disk whatever we had
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
                is ApiErrorResponse -> {
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }
            }
        }

    }

    // Returns a LiveData object that represents the resource that's implemented
    // in the base class.
    fun asLiveData() = result as LiveData<Resource<T>>

    // Called to determine if the resource should be fetched from the network.
    // This can be used to rate limit the network calls or to limit data usage.
    @MainThread
    protected open fun shouldFetch(data: T?) = true

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract fun saveCallResult(item: T)

    // Called to get the cached data from the database.
    @MainThread
    protected abstract fun loadFromDb(): LiveData<T>

    // Called to create the API call.
    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<T>>
}
