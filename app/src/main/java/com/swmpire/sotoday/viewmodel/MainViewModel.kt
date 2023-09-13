package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swmpire.sotoday.domain.usecase.GetTodayUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val getTodayUseCase: GetTodayUseCase) : ViewModel() {

    private val _liveData = MutableLiveData<List<String>?>()
    val liveData: LiveData<List<String>?> get() = _liveData

    fun fetchData() {
        viewModelScope.launch {
            _liveData.value = getTodayUseCase()
        }
    }
}