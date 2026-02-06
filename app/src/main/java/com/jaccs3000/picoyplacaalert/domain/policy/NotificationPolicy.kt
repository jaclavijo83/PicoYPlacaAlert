package com.picoyplacaalert.domain.policy

import com.picoyplacaalert.domain.model.NotificationEvaluation
import com.picoyplacaalert.domain.model.VehiclePlate

class NotificationPolicy {

    fun evaluate(
        affectedVehicles: List<VehiclePlate>
    ): NotificationEvaluation {
        return NotificationEvaluation(
            shouldNotify = affectedVehicles.isNotEmpty(),
            affectedVehicles = affectedVehicles
        )
    }
}
