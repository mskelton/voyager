package com.markskelton.voyager.db.models

import androidx.room.Embedded
import androidx.room.Relation

data class HydratedLog(
    @Embedded
    val log: Log,

    @Relation(parentColumn = "id", entityColumn = "id")
    var project: Project,

    @Relation(parentColumn = "id", entityColumn = "id")
    val vehicle: Vehicle
)
