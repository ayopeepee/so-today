package com.swmpire.sotoday.data.repository

import com.swmpire.sotoday.data.network.Parser
import com.swmpire.sotoday.domain.repository.DateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class DateRepositoryImpl(private val parser: Parser) : DateRepository {

    override suspend fun getToday(url: String): List<String> {
        return withContext(Dispatchers.IO) {
            parser.getData(url)
        }
    }


}