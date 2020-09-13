package com.markskelton.voyager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.markskelton.voyager.db.dao.LogDao
import com.markskelton.voyager.db.dao.ProjectDao
import com.markskelton.voyager.db.dao.VehicleDao
import com.markskelton.voyager.db.models.Log
import com.markskelton.voyager.db.models.Project
import com.markskelton.voyager.db.models.Vehicle

@Database(
    entities = [
        Log::class,
        Project::class,
        Vehicle::class
    ],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao
    abstract fun projectDao(): ProjectDao
    abstract fun vehicleDao(): VehicleDao
}
