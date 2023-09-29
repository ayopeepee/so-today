package com.swmpire.sotoday.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.swmpire.sotoday.data.repository.UnsplashRepository
import com.swmpire.sotoday.domain.model.Event
import com.swmpire.sotoday.domain.usecase.GetAllEventsByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetCurrentDateUseCase
import com.swmpire.sotoday.domain.usecase.GetDateUseCase
import com.swmpire.sotoday.domain.usecase.GetEventByDateUseCase
import com.swmpire.sotoday.domain.usecase.GetEventInEnglishUseCase
import com.swmpire.sotoday.domain.usecase.GetNotificationStateUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayAllEventsUseCase
import com.swmpire.sotoday.domain.usecase.GetTodayEventUseCase
import com.swmpire.sotoday.domain.usecase.SetNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val getTodayEventUseCase: GetTodayEventUseCase,
    private val getTodayAllEventsUseCase: GetTodayAllEventsUseCase,
    private val getEventByDateUseCase: GetEventByDateUseCase,
    private val getAllEventsByDateUseCase: GetAllEventsByDateUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getDateUseCase: GetDateUseCase,
    private val getNotificationStateUseCase: GetNotificationStateUseCase,
    private val setNotificationUseCase: SetNotificationUseCase,
    private val getEventInEnglishUseCase: GetEventInEnglishUseCase,
    private val unsplashRepository: UnsplashRepository
) : ViewModel() {

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?> = _event

    private val _allEvents = MutableLiveData<MutableList<Event>>()
    val allEvents: LiveData<MutableList<Event>> = _allEvents

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _isNotificationOn = MutableLiveData<Boolean>()
    val isNotificationOn: LiveData<Boolean> get() = _isNotificationOn

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        unsplashRepository.getSearchResults(queryString).cachedIn(viewModelScope)
    }
    init {
        _isNotificationOn.value = getNotificationStateUseCase.invoke()
    }
    fun fetchCurrentData() {
        viewModelScope.launch {
            _date.value = getCurrentDateUseCase.invoke()
            _event.value = getTodayEventUseCase.invoke()
            _allEvents.value = getTodayAllEventsUseCase.invoke()
            searchPhotos(getEventInEnglishUseCase.invoke(event.value?.name ?: DEFAULT_QUERY))
        }
    }

    fun fetchData(date: Date) {
        viewModelScope.launch {
            _date.value = getDateUseCase.invoke(date)
            _event.value = getEventByDateUseCase.invoke(date)
            _allEvents.value = getAllEventsByDateUseCase.invoke(date)
            searchPhotos(getEventInEnglishUseCase.invoke(event.value?.name ?: DEFAULT_QUERY))
        }
    }

    fun setReminderOn() {
        setNotificationUseCase.invoke(true)
        _isNotificationOn.value = true
    }

    fun setReminderOff() {
        setNotificationUseCase.invoke(false)
        _isNotificationOn.value = false
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }
    companion object {
        private const val DEFAULT_QUERY = "cats"
    }
}