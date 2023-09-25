package com.swmpire.sotoday.di

import android.content.Context
import com.swmpire.sotoday.data.network.Parser
import com.swmpire.sotoday.data.repository.DateRepositoryImpl
import com.swmpire.sotoday.data.repository.NotificationRepositoryImpl
import com.swmpire.sotoday.data.repository.TranslateRepositoryImpl
import com.swmpire.sotoday.domain.repository.DateRepository
import com.swmpire.sotoday.domain.repository.NotificationRepository
import com.swmpire.sotoday.domain.repository.TranslateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDateRepository(parser: Parser) : DateRepository {
        return DateRepositoryImpl(parser = parser)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(@ApplicationContext context: Context) : NotificationRepository {
        return NotificationRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideTranslateRepository() : TranslateRepository {
        return TranslateRepositoryImpl()
    }
}