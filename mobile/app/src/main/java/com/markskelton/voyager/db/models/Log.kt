package com.markskelton.voyager.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.markskelton.voyager.utils.epoch

@Entity
data class Log(
    @PrimaryKey val id: Int,
    val projectId: Int,
    val vehicleId: Int,
    val startMileage: Int,
    val endMileage: Int? = null,
    val timestamp: Long = epoch(),
)
