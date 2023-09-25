package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.repository.NotificationRepository

class GetNotificationStateUseCase(private val notificationRepository: NotificationRepository) {
    operator fun invoke() : Boolean {
        return notificationRepository.isNotificationOn()
    }
}