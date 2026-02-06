package com.picoyplacaalert.domain.policy

import com.picoyplacaalert.domain.repository.NotificationLogRepository
import java.time.LocalDate

class NotificationDeduplicationPolicy(
    private val repository: NotificationLogRepository
) {

    suspend fun canNotify(
        date: LocalDate,
        windowStartMinute: Int
    ): Boolean {
        return !repository.wasNotificationSent(date, windowStartMinute)
    }
}
