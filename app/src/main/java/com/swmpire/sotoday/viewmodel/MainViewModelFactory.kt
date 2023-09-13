package com.swmpire.sotoday.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swmpire.sotoday.data.network.Parser
import com.swmpire.sotoday.data.repository.DateRepositoryImpl
import com.swmpire.sotoday.domain.usecase.GetTodayUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    private val dateRepository by lazy(LazyThreadSafetyMode.NONE) {
        DateRepositoryImpl(parser = Parser())
    }

    private val getTodayUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetTodayUseCase(dateRepository = dateRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getTodayUseCase = getTodayUseCase) as T
    }
}