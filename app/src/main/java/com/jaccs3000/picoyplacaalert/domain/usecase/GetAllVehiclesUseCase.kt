package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.repository.VehicleRepository

class GetAllVehiclesUseCase(
    private val repository: VehicleRepository
) {

    operator fun invoke() = repository.getAllVehicles()
}
