package com.markskelton.voyager.repositories

import com.markskelton.voyager.db.dao.VehicleDao
import com.markskelton.voyager.db.models.Vehicle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor(
    private val vehicleDao: VehicleDao
) {
    fun list() = vehicleDao.getAll()

    fun create(name: String) {
        vehicleDao.insert(Vehicle(0, name))
    }

    fun delete(vehicle: Vehicle) {
        vehicleDao.delete(vehicle)
    }
}
