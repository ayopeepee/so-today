package com.swmpire.sotoday.data.repository

import com.swmpire.sotoday.data.parser.Parser
import com.swmpire.sotoday.domain.repository.DateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateRepositoryImpl(private val parser: Parser) : DateRepository {

    override suspend fun getEventList(url: String): MutableList<String> {
        return withContext(Dispatchers.IO) {
            parser.getEventList(url)
        }
    }

    override suspend fun getDayAndWeekday(date: Date): String {
        val dateFormat = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
        return dateFormat.format(date)
    }

    override suspend fun getEvent(url: String): String {
        return withContext(Dispatchers.IO) {
            parser.getFirstEvent(url)
        }
    }


}