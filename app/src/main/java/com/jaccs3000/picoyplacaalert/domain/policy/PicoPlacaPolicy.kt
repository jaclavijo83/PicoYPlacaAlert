package com.picoyplacaalert.domain.policy

import com.picoyplacaalert.domain.model.PicoPlacaRule
import java.time.LocalDate

class PicoPlacaPolicy {

    fun isPlateRestricted(
        plateLastDigit: Int,
        rules: List<PicoPlacaRule>,
        date: LocalDate
    ): Boolean {
        val dayOfWeek = date.dayOfWeek.value

        return rules.any { rule ->
            rule.dayOfWeek == dayOfWeek &&
                    rule.lastDigits.contains(plateLastDigit)
        }
    }
}
