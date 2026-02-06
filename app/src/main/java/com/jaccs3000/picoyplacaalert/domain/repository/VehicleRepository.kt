package com.picoyplacaalert.domain.repository

import com.picoyplacaalert.domain.model.VehiclePlate
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    suspend fun addVehicle(vehicle: VehiclePlate)

    suspend fun updateVehicle(vehicle: VehiclePlate)

    suspend fun deleteVehicle(vehicleId: Long)

    fun getAllVehicles(): Flow<List<VehiclePlate>>

    fun getActiveVehicles(): Flow<List<VehiclePlate>>
}
