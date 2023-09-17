package com.swmpire.sotoday.domain.repository

interface EventRepository {

    suspend fun getToday(url: String) : List<String>

}