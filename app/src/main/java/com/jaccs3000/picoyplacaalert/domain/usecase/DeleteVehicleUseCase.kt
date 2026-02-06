package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.repository.VehicleRepository

class DeleteVehicleUseCase(
    private val repository: VehicleRepository
) {

    suspend operator fun invoke(vehicleId: Long) {
        repository.deleteVehicle(vehicleId)
    }
}
