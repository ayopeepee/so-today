package com.swmpire.sotoday.di

import com.swmpire.sotoday.data.network.Parser
import com.swmpire.sotoday.data.repository.DateRepositoryImpl
import com.swmpire.sotoday.domain.repository.DateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}