package com.picoyplacaalert.domain.model

data class TimeWindow(
    val startMinutesOfDay: Int,
    val endMinutesOfDay: Int
) {
    init {
        require(startMinutesOfDay in 0..1439)
        require(endMinutesOfDay in 1..1440)
        require(startMinutesOfDay < endMinutesOfDay)
    }
}
