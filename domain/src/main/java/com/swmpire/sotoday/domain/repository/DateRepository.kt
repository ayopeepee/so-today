package com.swmpire.sotoday.domain.repository

import java.util.Date

interface DateRepository {

    suspend fun getEventList(url: String) : MutableList<String>

    suspend fun getDayAndWeekday(date: Date) : String

    suspend fun getEvent(url: String) : String

}