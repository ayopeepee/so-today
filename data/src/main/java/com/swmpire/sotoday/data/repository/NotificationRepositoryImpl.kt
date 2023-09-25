package com.swmpire.sotoday.data.repository

import android.content.Context
import android.util.Log
import com.swmpire.sotoday.domain.repository.NotificationRepository

class NotificationRepositoryImpl(context: Context) : NotificationRepository {

    private val prefs = context.getSharedPreferences("notification_prefs", Context.MODE_PRIVATE)
    override fun setNotificationState(state: Boolean) {
        prefs.edit().putBoolean("notification_state", state).apply()
        Log.d("TAG", "setNotificationState: $state")
    }

    override fun isNotificationOn(): Boolean = prefs.getBoolean("notification_state", false)
}