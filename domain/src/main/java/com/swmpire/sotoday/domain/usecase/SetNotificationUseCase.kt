package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.repository.NotificationRepository

class SetNotificationUseCase(private val notificationRepository: NotificationRepository) {
    operator fun invoke(state: Boolean) {
        notificationRepository.setNotificationState(state = state)
    }
}