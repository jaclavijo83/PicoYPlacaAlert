package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.model.NotificationEvaluation
import com.picoyplacaalert.domain.model.VehiclePlate
import com.picoyplacaalert.domain.policy.NotificationPolicy
import com.picoyplacaalert.domain.policy.PicoPlacaPolicy
import java.time.LocalDate

class EvaluatePicoPlacaForDayUseCase(
    private val picoPlacaPolicy: PicoPlacaPolicy,
    private val notificationPolicy: NotificationPolicy
) {

    operator fun invoke(
        vehicles: List<VehiclePlate>,
        rules: List<com.picoyplacaalert.domain.model.PicoPlacaRule>,
        date: LocalDate
    ): NotificationEvaluation {

        val affected = vehicles.filter { vehicle ->
            val lastDigit = vehicle.plate.last { it.isDigit() }.digitToInt()
            picoPlacaPolicy.isPlateRestricted(lastDigit, rules, date)
        }

        return notificationPolicy.evaluate(affected)
    }
}
