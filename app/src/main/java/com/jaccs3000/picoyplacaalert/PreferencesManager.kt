package com.jaccs3000.picoyplacaalert

import android.content.Context
import java.time.DayOfWeek
import java.time.LocalDateTime

class PreferencesManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("pico_placa_prefs", Context.MODE_PRIVATE)

    fun saveMorningRange(value: String) =
        prefs.edit().putString("morningRange", value).apply()

    fun getMorningRange(): String =
        prefs.getString("morningRange", "") ?: ""

    fun saveEveningRange(value: String) =
        prefs.edit().putString("eveningRange", value).apply()

    fun getEveningRange(): String =
        prefs.getString("eveningRange", "") ?: ""

    fun savePlate(value: String) =
        prefs.edit().putString("plate", value).apply()

    fun getPlate(): String =
        prefs.getString("plate", "") ?: ""

    fun saveDigitsForDay(day: String, digits: String) =
        prefs.edit().putString("digits_$day", digits).apply()

    fun getDigitsForDay(day: String): String =
        prefs.getString("digits_$day", "") ?: ""

    fun getDigitsForToday(): List<Int> {

        val todayLetter = when (LocalDateTime.now().dayOfWeek) {
            DayOfWeek.MONDAY -> "L"
            DayOfWeek.TUESDAY -> "M"
            DayOfWeek.WEDNESDAY -> "X"
            DayOfWeek.THURSDAY -> "J"
            DayOfWeek.FRIDAY -> "V"
            else -> return emptyList()
        }

        return getDigitsForDay(todayLetter)
            .split(",")
            .mapNotNull { it.trim().toIntOrNull() }
    }

    fun saveStartMorning(value: String) =
        prefs.edit().putString("startMorning", value).apply()

    fun getStartMorning(): String =
        prefs.getString("startMorning", "") ?: ""

    fun saveEndMorning(value: String) =
        prefs.edit().putString("endMorning", value).apply()

    fun getEndMorning(): String =
        prefs.getString("endMorning", "") ?: ""

    fun saveStartEvening(value: String) =
        prefs.edit().putString("startEvening", value).apply()

    fun getStartEvening(): String =
        prefs.getString("startEvening", "") ?: ""

    fun saveEndEvening(value: String) =
        prefs.edit().putString("endEvening", value).apply()

    fun getEndEvening(): String =
        prefs.getString("endEvening", "") ?: ""
}
