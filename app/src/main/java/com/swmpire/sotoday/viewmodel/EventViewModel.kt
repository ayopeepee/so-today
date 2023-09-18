package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swmpire.sotoday.domain.model.Event
import com.swmpire.sotoday.domain.usecase.GetAllEventsByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetCurrentDateUseCase
import com.swmpire.sotoday.domain.usecase.GetDateUseCase
import com.swmpire.sotoday.domain.usecase.GetEventByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayAllEventsUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayEventUseCase
import kotlinx.coroutines.launch
import java.util.Date

class EventViewModel(
    private val getTodayEventUseCase: GetTodayEventUseCase,
    private val getTodayAllEventsUseCase: GetTodayAllEventsUseCase,
    private val getEventByDateUseCase: GetEventByDateUseCase,
    private val getAllEventsByDateUseCase: GetAllEventsByDateUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getDateUseCase: GetDateUseCase
) : ViewModel() {

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?> = _event

    private val _allEvents = MutableLiveData<MutableList<Event>>()
    val allEvents: LiveData<MutableList<Event>> = _allEvents

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    fun fetchCurrentData() {
        viewModelScope.launch {
            _event.value = getTodayEventUseCase.invoke()
            _allEvents.value = getTodayAllEventsUseCase.invoke()
            _date.value = getCurrentDateUseCase.invoke()
        }
    }

    fun fetchData(date: Date) {
        viewModelScope.launch {
            _event.value = getEventByDateUseCase.invoke(date)
            _allEvents.value = getAllEventsByDateUseCase.invoke(date)
            _date.value = getDateUseCase.invoke(date)
        }
    }
}