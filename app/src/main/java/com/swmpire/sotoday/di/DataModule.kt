package com.swmpire.sotoday.di

import android.content.Context
import com.swmpire.sotoday.data.parser.Parser
import com.swmpire.sotoday.data.repository.DateRepositoryImpl
import com.swmpire.sotoday.data.repository.NotificationRepositoryImpl
import com.swmpire.sotoday.data.repository.TranslateRepositoryImpl
import com.swmpire.sotoday.data.unsplash.UnsplashApi
import com.swmpire.sotoday.domain.repository.DateRepository
import com.swmpire.sotoday.domain.repository.NotificationRepository
import com.swmpire.sotoday.domain.repository.TranslateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit) : UnsplashApi =
        retrofit.create(UnsplashApi::class.java)

}