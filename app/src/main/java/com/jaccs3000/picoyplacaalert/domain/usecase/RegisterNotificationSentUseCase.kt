package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.repository.NotificationLogRepository
import java.time.LocalDate

class RegisterNotificationSentUseCase(
    private val repository: NotificationLogRepository
) {

    suspend operator fun invoke(
        date: LocalDate,
        windowStartMinute: Int
    ) {
        repository.registerNotification(date, windowStartMinute)
    }
}
