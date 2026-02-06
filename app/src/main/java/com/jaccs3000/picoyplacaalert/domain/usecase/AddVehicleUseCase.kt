package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.model.VehiclePlate
import com.picoyplacaalert.domain.repository.VehicleRepository

class AddVehicleUseCase(
    private val repository: VehicleRepository
) {

    suspend operator fun invoke(vehicle: VehiclePlate) {
        repository.addVehicle(vehicle)
    }
}
