package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.model.Event
import com.swmpire.sotoday.domain.repository.EventRepository
import java.util.Calendar

class GetTodayEventUseCase(private val eventRepository: EventRepository) {

    suspend operator fun invoke(): Event {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)

        val monthInRussian = getMonthInRussian(month)
        val url = "https://kakoysegodnyaprazdnik.ru/baza/$monthInRussian/$day"

        val response = eventRepository.getToday(url)
        return Event(day.toString(), month.toString(), response.first())
    }

    private fun getMonthInRussian(month: Int): String {
        val monthsInRussian = arrayOf(
            "yanvar", "fevral", "mart", "aprel", "may", "iyun",
            "iyul", "avgust", "sentyabr", "oktyabr", "noyabr", "dekabr"
        )
        return monthsInRussian[month]
    }
}