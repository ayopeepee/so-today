package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swmpire.sotoday.domain.model.Event
import com.swmpire.sotoday.domain.usecase.GetTodayAllEventsUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayEventUseCase
import kotlinx.coroutines.launch

class EventViewModel(
    private val getTodayEventUseCase: GetTodayEventUseCase,
    private val getTodayAllEventsUseCase: GetTodayAllEventsUseCase) : ViewModel() {

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?> = _event

    private val _allEvents = MutableLiveData<List<Event>?>()
    val allEvents: LiveData<List<Event>?> = _allEvents

    init {
        fetchData()
    }
    fun fetchData() {
        viewModelScope.launch {
            _event.value = getTodayEventUseCase.invoke()
            _allEvents.value = getTodayAllEventsUseCase.invoke()
        }
    }
}