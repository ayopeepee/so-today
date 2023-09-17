package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.repository.CalendarRepository
import java.util.Date

class GetNextMonthDatesUseCase(private val calendarRepository: CalendarRepository) {
    operator fun invoke() : List<Date> {
        return calendarRepository.getDatesOfNextMonth()
    }
}