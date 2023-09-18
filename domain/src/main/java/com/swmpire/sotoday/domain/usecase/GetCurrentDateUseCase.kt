package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.repository.DateRepository
import java.util.Calendar

class GetCurrentDateUseCase(private val dateRepository: DateRepository) {
    suspend operator fun invoke() : String {
        return dateRepository.getDayAndWeekday(Calendar.getInstance().time)
    }
}