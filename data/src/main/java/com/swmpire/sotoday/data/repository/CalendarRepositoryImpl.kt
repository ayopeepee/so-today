package com.swmpire.sotoday.data.repository

import com.swmpire.sotoday.domain.repository.CalendarRepository
import java.util.Calendar
import java.util.Date

class CalendarRepositoryImpl() : CalendarRepository {

    private val calendar = Calendar.getInstance()

    init {
        calendar.time = Date()
    }

    override fun getCurrentMonthFutureDays(): List<Date> {
        return getDates(mutableListOf())
    }

    override fun getDatesOfNextMonth(): List<Date> {
        calendar.add(Calendar.MONTH, 1)
        return getDates(mutableListOf())
    }

    override fun getDatesOfPreviousMonth(): List<Date> {
        calendar.add(Calendar.MONTH, -1)
        return getDates(mutableListOf())
    }

    private fun getDates(list: MutableList<Date>): List<Date> {
        resetDayOfMonth()
        list.add(calendar.time)

        val currentMonth = calendar[Calendar.MONTH]

        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, 1)

            if (currentMonth == calendar[Calendar.MONTH])
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)

        return list
    }

    private fun resetDayOfMonth() {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
    }
}