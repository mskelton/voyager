package com.markskelton.voyager.repositories

import com.markskelton.voyager.db.dao.LogDao
import com.markskelton.voyager.db.models.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogRepository @Inject constructor(
    private val logDao: LogDao,
) {
    fun listLogs() = logDao.getHydratedLogs()

    fun createLog(log: Log) = logDao.insert(log)
}
