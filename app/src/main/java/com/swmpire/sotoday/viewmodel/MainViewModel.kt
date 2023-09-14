package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swmpire.sotoday.domain.model.Event
import com.swmpire.sotoday.domain.usecase.GetTodayEventUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getTodayEventUseCase: GetTodayEventUseCase) : ViewModel() {

    private val _liveData = MutableLiveData<Event?>()
    val liveData: LiveData<Event?> get() = _liveData

    fun fetchData() {
        viewModelScope.launch {
            _liveData.value = getTodayEventUseCase.invoke()
        }
    }
}