package com.swmpire.sotoday.domain.repository

interface NotificationRepository {

    fun setNotificationState(state: Boolean)

    fun isNotificationOn() : Boolean
}