package com.markskelton.voyager.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
