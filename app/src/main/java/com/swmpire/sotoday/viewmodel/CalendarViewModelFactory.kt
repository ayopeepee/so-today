package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swmpire.sotoday.data.repository.CalendarRepositoryImpl
import com.swmpire.sotoday.domain.usecase.GetFutureDaysOfCurrentMonthUseCase
import com.swmpire.sotoday.domain.usecase.GetNextMonthDatesUseCase
import com.swmpire.sotoday.domain.usecase.GetPreviousMonthDatesUseCase

class CalendarViewModelFactory : ViewModelProvider.Factory {

    private val calendarRepository by lazy(LazyThreadSafetyMode.NONE) {
        CalendarRepositoryImpl()
    }

    private val getFutureDaysOfCurrentMonthUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetFutureDaysOfCurrentMonthUseCase(calendarRepository = calendarRepository)
    }

    private val getNextMonthDatesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetNextMonthDatesUseCase(calendarRepository = calendarRepository)
    }

    private val getPreviousMonthDatesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetPreviousMonthDatesUseCase(calendarRepository = calendarRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalendarViewModel(
            getFutureDaysOfCurrentMonthUseCase = getFutureDaysOfCurrentMonthUseCase,
            getNextMonthDatesUseCase = getNextMonthDatesUseCase,
            getPreviousMonthDatesUseCase = getPreviousMonthDatesUseCase
        ) as T
    }
}