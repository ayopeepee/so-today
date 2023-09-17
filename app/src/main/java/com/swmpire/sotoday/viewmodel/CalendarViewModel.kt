package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swmpire.sotoday.domain.usecase.GetFutureDaysOfCurrentMonthUseCase
import com.swmpire.sotoday.domain.usecase.GetNextMonthDatesUseCase
import com.swmpire.sotoday.domain.usecase.GetPreviousMonthDatesUseCase
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class CalendarViewModel(
    private val getFutureDaysOfCurrentMonthUseCase: GetFutureDaysOfCurrentMonthUseCase,
    private val getNextMonthDatesUseCase: GetNextMonthDatesUseCase,
    private val getPreviousMonthDatesUseCase: GetPreviousMonthDatesUseCase,
) : ViewModel() {

    private val _dates = MutableLiveData<List<Date>>()
    val dates: LiveData<List<Date>> get() = _dates

    private val _selectedDate = MutableLiveData<Date?>()
    val selectedDate: LiveData<Date?> get() = _selectedDate

    fun selectDate(date: Date?) {
        _selectedDate.value = date
    }

    fun loadFutureMonthDates() {
        viewModelScope.launch {
            _dates.value = getFutureDaysOfCurrentMonthUseCase.invoke()
        }

    }

    fun loadNextMonthDates() {
        viewModelScope.launch {
            _dates.value = getNextMonthDatesUseCase.invoke()
        }

    }

    fun loadPreviousMonthDates() {
        viewModelScope.launch {
            _dates.value = getPreviousMonthDatesUseCase.invoke()
        }

    }

    fun loadCurrentDate() {
        _selectedDate.value = Calendar.getInstance().time
        loadFutureMonthDates()
    }
}