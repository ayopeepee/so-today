package com.swmpire.sotoday.data.repository

import com.swmpire.sotoday.data.network.Parser
import com.swmpire.sotoday.domain.repository.DateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateRepositoryImpl(private val parser: Parser) : DateRepository {

    override suspend fun getEventList(url: String): MutableList<String> {
        return withContext(Dispatchers.IO) {
            parser.getData(url)
        }
    }

    override suspend fun getDayAndWeekday(date: Date): String {
        val dateFormat = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
        return dateFormat.format(date)
    }


}