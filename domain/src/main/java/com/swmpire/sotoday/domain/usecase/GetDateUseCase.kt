package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.repository.DateRepository
import java.util.Date

class GetDateUseCase(private val dateRepository: DateRepository) {
    suspend operator fun invoke(date: Date) : String {
        return dateRepository.getDayAndWeekday(date)
    }
}