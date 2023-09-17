package com.swmpire.sotoday.domain.repository

import java.util.Date

interface CalendarRepository {

    fun getCurrentMonthFutureDays(): List<Date>

    fun getDatesOfNextMonth(): List<Date>

    fun getDatesOfPreviousMonth(): List<Date>

}