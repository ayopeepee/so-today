package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.model.Event
import com.swmpire.sotoday.domain.repository.DateRepository
import java.util.Calendar
import java.util.Date

class GetEventByDateUseCase(private val dateRepository: DateRepository) {

    suspend operator fun invoke(date: Date) : Event {

        val calendar = Calendar.getInstance().apply {
            time = date
        }

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)

        val monthInRussian = getMonthInRussian(month)

        val url = "https://kakoysegodnyaprazdnik.ru/baza/$monthInRussian/$day"

        val response = dateRepository.getEvent(url)
        return Event(response)

    }


    private fun getMonthInRussian(month: Int): String {
        val monthsInRussian = arrayOf(
            "yanvar", "fevral", "mart", "aprel", "may", "iyun",
            "iyul", "avgust", "sentyabr", "oktyabr", "noyabr", "dekabr"
        )
        return monthsInRussian[month]
    }
}