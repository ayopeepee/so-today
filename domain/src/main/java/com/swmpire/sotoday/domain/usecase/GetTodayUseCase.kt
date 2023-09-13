package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.repository.DateRepository
import java.util.Calendar

class GetTodayUseCase(private val dateRepository: DateRepository) {

    suspend operator fun invoke() : List<String> {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)

        val monthInRussian = getMonthInRussian(month)
        val url = "https://kakoysegodnyaprazdnik.ru/baza/$monthInRussian/$day"

        return dateRepository.getToday(url)
    }
    }


    private fun getMonthInRussian(month: Int): String {
        val monthsInRussian = arrayOf(
            "yanvar", "fevral", "mart", "aprel", "may", "iyun",
            "iyul", "avgust", "sentyabr", "oktyabr", "noyabr", "dekabr"
        )
        return monthsInRussian[month]
}