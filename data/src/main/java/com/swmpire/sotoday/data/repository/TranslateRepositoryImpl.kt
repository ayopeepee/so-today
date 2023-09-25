package com.swmpire.sotoday.data.repository

import android.util.Log
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.swmpire.sotoday.domain.repository.TranslateRepository
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TranslateRepositoryImpl : TranslateRepository {
    override suspend fun getTranslation(text: String): String = suspendCoroutine { continuation ->
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.RUSSIAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val russianEnglishTranslator = Translation.getClient(options)
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        russianEnglishTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                Log.d("TAG", "getTranslation: model is set")

                russianEnglishTranslator.translate(text)
                    .addOnSuccessListener {
                        Log.d("TAG", "getTranslation: $text --- $it")
                        continuation.resume(it)
                    }
                    .addOnFailureListener {
                        Log.d("TAG", "getTranslation: translate error: $it")
                        continuation.resumeWithException(it)
                    }
            }
            .addOnFailureListener {
                Log.d("TAG", "getTranslation: model error: $it")
                continuation.resumeWithException(it)
            }

    }
}