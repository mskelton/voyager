package com.markskelton.voyager.repositories

import com.markskelton.voyager.api.WebService
import com.markskelton.voyager.db.dao.LogDao
import com.markskelton.voyager.db.dao.ProjectDao
import com.markskelton.voyager.db.dao.VehicleDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogRepository @Inject constructor(
    private val webService: WebService,
    private val logDao: LogDao,
    private val projectDao: ProjectDao,
    private val vehicleDao: VehicleDao
) {
    fun listLogs() = logDao.getHydratedLogs()

//    fun getLog(userId: String): LiveData<Resource<Log>> {
//        return object : NetworkBoundResource<Log>() {
//
//            override fun createCall(): LiveData<ApiResponse<Log>> {
//                return webService.getLog(userId)
//            }
//        }.asLiveData()
//    }
}
