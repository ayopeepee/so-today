package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swmpire.sotoday.data.network.Parser
import com.swmpire.sotoday.data.repository.DateRepositoryImpl
import com.swmpire.sotoday.domain.usecase.GetAllEventsByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetCurrentDateUseCase
import com.swmpire.sotoday.domain.usecase.GetDateUseCase
import com.swmpire.sotoday.domain.usecase.GetEventByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayAllEventsUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayEventUseCase

class EventViewModelFactory : ViewModelProvider.Factory {

    private val dateRepository by lazy(LazyThreadSafetyMode.NONE) {
        DateRepositoryImpl(parser = Parser())
    }

    private val getTodayEventUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetTodayEventUseCase(dateRepository = dateRepository)
    }

    private val getTodayAllEventsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetTodayAllEventsUseCase(dateRepository = dateRepository)
    }

    private val getEventByDateUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetEventByDateUseCase(dateRepository = dateRepository)
    }

    private val getAllEventsByDateUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetAllEventsByDateUseCase(dateRepository = dateRepository)
    }

    private val getCurrentDateUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetCurrentDateUseCase(dateRepository = dateRepository)
    }

    private val getDateUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetDateUseCase(dateRepository = dateRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventViewModel(
            getTodayEventUseCase = getTodayEventUseCase,
            getTodayAllEventsUseCase = getTodayAllEventsUseCase,
            getEventByDateUseCase = getEventByDateUseCase,
            getAllEventsByDateUseCase = getAllEventsByDateUseCase,
            getCurrentDateUseCase = getCurrentDateUseCase,
            getDateUseCase = getDateUseCase
        ) as T
    }
}