package com.swmpire.sotoday.data.repository

import android.util.Log
import com.swmpire.sotoday.domain.repository.TranslateRepository
import me.bush.translator.Language
import me.bush.translator.Translator

class TranslateRepositoryImpl : TranslateRepository {
    override suspend fun getTranslation(text: String): String {
        val translator = Translator()
        val translation = translator.translate(text, Language.ENGLISH, Language.RUSSIAN)
        Log.d("TAG", "getTranslation: ru: ${translation.sourceText} en: ${translation.translatedText}")
        return translation.translatedText
    }
}