package com.swmpire.sotoday.util

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar
import java.util.Locale

object ReminderManager {

    const val REMINDER_NOTIFICATION_REQUEST_CODE = 123
    @SuppressLint("ScheduleExactAlarm")
    fun startReminder(
        context: Context,
        reminderTime: String = "08:00",
        reminderText: String = "Some day",
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val (hours, min) = reminderTime.split(":").map { it.toInt() }
        val intent =
            Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
                intent.putExtra("reminderText", reminderText)
                PendingIntent.getBroadcast(
                    context.applicationContext,
                    reminderId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

        val calendar: Calendar = Calendar.getInstance(Locale.getDefault()).apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, min)
        }

        if (Calendar.getInstance(Locale.getDefault())
                .apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0
        ) {
            calendar.add(Calendar.DATE, 1)
        }

        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis, intent),
            intent
        )
    }

    fun stopReminder(
        context: Context,
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                0
            )
        }
        alarmManager.cancel(intent)
    }
}