package com.jaccs3000.picoyplacaalert

import java.time.DayOfWeek
import java.time.ZoneId
import java.time.ZonedDateTime

object PicoPlacaLogic {

    fun hasPicoYPlaca(
        plate: String,
        digitsToday: List<Int>,
        startMorning: Int,
        endMorning: Int,
        startEvening: Int,
        endEvening: Int
    ): Boolean {

        val lastDigit = plate
            .uppercase()
            .lastOrNull { it.isDigit() }
            ?.digitToInt() ?: return false

        val now = ZonedDateTime.now(ZoneId.of("America/Bogota"))
        val currentHour = now.hour
        val today = now.dayOfWeek

        println("DEBUG -> Hoy es: $today")
        println("DEBUG -> Hora actual: $currentHour")
        println("DEBUG -> Último dígito: $lastDigit")
        println("DEBUG -> Dígitos hoy: $digitsToday")

        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
            return false
        }

        val digitMatch = digitsToday.contains(lastDigit)
        val morningMatch = currentHour in startMorning until endMorning
        val eveningMatch = currentHour in startEvening until endEvening

        println("DEBUG -> digitMatch: $digitMatch")
        println("DEBUG -> morningMatch: $morningMatch")
        println("DEBUG -> eveningMatch: $eveningMatch")

        return digitMatch && (morningMatch || eveningMatch)
    }

}
