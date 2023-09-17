package com.swmpire.sotoday.data.repository

import com.swmpire.sotoday.data.network.Parser
import com.swmpire.sotoday.domain.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepositoryImpl(private val parser: Parser) : EventRepository {

    override suspend fun getToday(url: String): List<String> {
        return withContext(Dispatchers.IO) {
            parser.getData(url)
        }
    }


}