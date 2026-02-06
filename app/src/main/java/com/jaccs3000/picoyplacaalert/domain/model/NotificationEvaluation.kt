package com.picoyplacaalert.domain.model

data class NotificationEvaluation(
    val shouldNotify: Boolean,
    val affectedVehicles: List<VehiclePlate>
)
