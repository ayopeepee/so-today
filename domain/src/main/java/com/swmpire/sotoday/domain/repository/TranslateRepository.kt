package com.swmpire.sotoday.domain.repository

interface TranslateRepository {
    suspend fun getTranslation(text: String) : String
}