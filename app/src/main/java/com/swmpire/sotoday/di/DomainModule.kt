package com.swmpire.sotoday.di

import com.swmpire.sotoday.domain.repository.DateRepository
import com.swmpire.sotoday.domain.usecase.GetAllEventsByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetCurrentDateUseCase
import com.swmpire.sotoday.domain.usecase.GetDateUseCase
import com.swmpire.sotoday.domain.usecase.GetEventByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayAllEventsUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayEventUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetTodayEventUseCase(dateRepository: DateRepository)
    : GetTodayEventUseCase = GetTodayEventUseCase(dateRepository = dateRepository)

    @Provides
    fun provideGetTodayAllEventsUseCase(dateRepository: DateRepository)
    : GetTodayAllEventsUseCase = GetTodayAllEventsUseCase(dateRepository = dateRepository)

    @Provides
    fun provideGetEventByDateUseCase(dateRepository: DateRepository)
    : GetEventByDateUseCase = GetEventByDateUseCase(dateRepository = dateRepository)

    @Provides
    fun provideGetAllEventsByDateUseCase(dateRepository: DateRepository)
    : GetAllEventsByDateUseCase = GetAllEventsByDateUseCase(dateRepository = dateRepository)

    @Provides
    fun provideGetCurrentDateUseCase(dateRepository: DateRepository)
    : GetCurrentDateUseCase = GetCurrentDateUseCase(dateRepository = dateRepository)

    @Provides
    fun provideGetDateUseCase(dateRepository: DateRepository)
    : GetDateUseCase = GetDateUseCase(dateRepository = dateRepository)
}