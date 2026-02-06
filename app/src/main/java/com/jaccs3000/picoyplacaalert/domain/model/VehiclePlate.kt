package com.picoyplacaalert.domain.model

data class VehiclePlate(
    val id: Long = 0,
    val plate: String,
    val alias: String,
    val isActive: Boolean
)
