package com.markskelton.voyager.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Project(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override val name: String
) : NamedEntity
