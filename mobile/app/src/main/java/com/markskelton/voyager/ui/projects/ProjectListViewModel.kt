package com.markskelton.voyager.ui.projects

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.markskelton.voyager.repositories.ProjectRepository

class ProjectListViewModel @ViewModelInject constructor(
    projectRepository: ProjectRepository
) : ViewModel() {

    var projects = projectRepository.list()
}

