package com.swmpire.sotoday.domain.usecase

import com.swmpire.sotoday.domain.repository.TranslateRepository

class GetEventInEnglishUseCase(private val translateRepository: TranslateRepository) {
    suspend operator fun invoke(text: String) : String {
        return translateRepository.getTranslation(text = text)
    }
}