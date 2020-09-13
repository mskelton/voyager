package com.markskelton.voyager.ui.log.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.markskelton.voyager.repositories.LogRepository

class LogListViewModel @ViewModelInject constructor(
    logRepository: LogRepository
) : ViewModel() {

    var logs = logRepository.listLogs()
}

