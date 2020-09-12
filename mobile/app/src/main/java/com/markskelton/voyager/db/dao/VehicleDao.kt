package com.markskelton.voyager.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markskelton.voyager.db.models.Vehicle

@Dao
interface VehicleDao {
    @Query("SELECT * FROM Vehicle")
    fun getAll(): LiveData<List<Vehicle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vehicle: Vehicle)

    @Delete
    fun delete(vehicle: Vehicle)
}
