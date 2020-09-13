package com.markskelton.voyager.ui.log.create

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.markskelton.voyager.db.models.Project
import com.markskelton.voyager.db.models.Vehicle
import com.markskelton.voyager.repositories.ProjectRepository
import com.markskelton.voyager.repositories.VehicleRepository

class CreateLogViewModel @ViewModelInject constructor(
    projectRepository: ProjectRepository,
    vehicleRepository: VehicleRepository
) : ViewModel() {

    var projects = projectRepository.list()
    var vehicles = vehicleRepository.list()

    var selectedProject: Project? = null
    var selectedVehicle: Vehicle? = null
}

