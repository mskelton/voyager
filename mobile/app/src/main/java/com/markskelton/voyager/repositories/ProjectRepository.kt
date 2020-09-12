package com.markskelton.voyager.repositories

import com.markskelton.voyager.db.dao.ProjectDao
import com.markskelton.voyager.db.models.Project
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val projectDao: ProjectDao
) {
    fun list() = projectDao.getAll()

    fun create(name: String) {
        projectDao.insert(Project(0, name))
    }

    fun delete(project: Project) {
        projectDao.delete(project)
    }
}
