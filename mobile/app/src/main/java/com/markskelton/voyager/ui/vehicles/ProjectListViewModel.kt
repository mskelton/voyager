package com.markskelton.voyager.ui.vehicles

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.markskelton.voyager.repositories.VehicleRepository

class VehicleListViewModel @ViewModelInject constructor(
    vehicleRepository: VehicleRepository
) : ViewModel() {

    var vehicles = vehicleRepository.list()
}

