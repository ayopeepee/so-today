package com.swmpire.sotoday.domain.repository

interface DateRepository {

    suspend fun getToday(url: String) : List<String>

}