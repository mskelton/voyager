package com.markskelton.voyager.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.markskelton.voyager.db.models.HydratedLog
import com.markskelton.voyager.db.models.Log

@Dao
interface LogDao {
    @Query("SELECT * FROM Log")
    fun getAll(): List<Log>

    @Transaction
    @Query("SELECT * FROM Log")
    fun getHydratedLogs(): LiveData<List<HydratedLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(log: Log)

    @Delete
    fun delete(log: Log)
}
