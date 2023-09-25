package com.swmpire.sotoday.di

import com.swmpire.sotoday.domain.repository.DateRepository
import com.swmpire.sotoday.domain.repository.NotificationRepository
import com.swmpire.sotoday.domain.repository.TranslateRepository
import com.swmpire.sotoday.domain.usecase.GetAllEventsByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetCurrentDateUseCase
import com.swmpire.sotoday.domain.usecase.GetDateUseCase
import com.swmpire.sotoday.domain.usecase.GetEventByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetEventInEnglishUseCase
import com.swmpire.sotoday.domain.usecase.GetNotificationStateUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayAllEventsUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayEventUseCase
import com.swmpire.sotoday.domain.usecase.SetNotificationUseCase
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

    @Provides
    fun provideGetNotificationStateUseCase(notificationRepository: NotificationRepository)
    : GetNotificationStateUseCase = GetNotificationStateUseCase(notificationRepository = notificationRepository)

    @Provides
    fun provideSetNotificationUseCase(notificationRepository: NotificationRepository)
    : SetNotificationUseCase = SetNotificationUseCase(notificationRepository = notificationRepository)

    @Provides
    fun provideGetEventInEnglishUseCase(translateRepository: TranslateRepository)
    : GetEventInEnglishUseCase = GetEventInEnglishUseCase(translateRepository = translateRepository)
}