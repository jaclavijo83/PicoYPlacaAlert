package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.repository.VehicleRepository

class GetActiveVehiclesUseCase(
    private val repository: VehicleRepository
) {

    operator fun invoke() = repository.getActiveVehicles()
}
