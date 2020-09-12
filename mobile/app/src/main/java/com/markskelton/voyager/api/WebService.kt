package com.markskelton.voyager.api

import androidx.lifecycle.LiveData
import com.markskelton.voyager.db.models.HydratedLog
import com.markskelton.voyager.db.models.Log
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("/logs")
    fun listLogs(): LiveData<ApiResponse<List<HydratedLog>>>

    @GET("/logs/{id}")
    fun getLog(@Path("id") id: String): LiveData<ApiResponse<Log>>
}
