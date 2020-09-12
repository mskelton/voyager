package com.markskelton.voyager.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.markskelton.voyager.db.models.Project

@Dao
interface ProjectDao {
    @Query("SELECT * FROM Project")
    fun getAll(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(project: Project)

    @Delete
    fun delete(project: Project)
}
