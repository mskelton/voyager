package com.markskelton.voyager.ui.log.list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.markskelton.voyager.repositories.LogRepository

class LogListViewModel @ViewModelInject constructor(
    logRepository: LogRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var logs = logRepository.listLogs()

    fun refresh() {
//        logs.value =
    }
}

