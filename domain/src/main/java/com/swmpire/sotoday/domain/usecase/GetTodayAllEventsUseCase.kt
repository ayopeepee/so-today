package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.model.Event
import com.swmpire.sotoday.domain.repository.DateRepository
import java.util.Calendar

class GetTodayAllEventsUseCase(private val dateRepository: DateRepository) {

    suspend operator fun invoke() : List<Event> {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)

        val monthInRussian = getMonthInRussian(month)
        val url = "https://kakoysegodnyaprazdnik.ru/baza/$monthInRussian/$day"

        val response = dateRepository.getToday(url)
        val listWithoutFirstElem: MutableList<String> = response.toMutableList()
        if (listWithoutFirstElem.isNotEmpty()) {
            listWithoutFirstElem.removeAt(0)
        }
        return listWithoutFirstElem.map { Event(day.toString(), month.toString(), it) }
    }
    }


    private fun getMonthInRussian(month: Int): String {
        val monthsInRussian = arrayOf(
            "yanvar", "fevral", "mart", "aprel", "may", "iyun",
            "iyul", "avgust", "sentyabr", "oktyabr", "noyabr", "dekabr"
        )
        return monthsInRussian[month]
}