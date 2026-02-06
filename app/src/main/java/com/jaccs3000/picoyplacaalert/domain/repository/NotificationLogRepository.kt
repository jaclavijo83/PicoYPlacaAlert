package com.picoyplacaalert.domain.repository

import java.time.LocalDate

interface NotificationLogRepository {

    suspend fun wasNotificationSent(
        date: LocalDate,
        windowStartMinute: Int
    ): Boolean

    suspend fun registerNotification(
        date: LocalDate,
        windowStartMinute: Int
    )
}
