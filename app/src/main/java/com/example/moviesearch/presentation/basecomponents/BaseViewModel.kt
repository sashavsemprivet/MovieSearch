package com.example.moviesearch.presentation.basecomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val progress = MutableStateFlow(false)
    val error = MutableStateFlow(false)

    private var lastWork: (suspend CoroutineScope.() -> Unit)? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        error.value = true
        progress.value = false
    }
    var canceledJob: Job? = null

    fun doWork(showProgressBar: Boolean = true, work: suspend CoroutineScope.() -> Unit) {
        canceledJob = viewModelScope.launch(Dispatchers.Default + exceptionHandler) {
            error.value = false
            if (showProgressBar) progress.value = true
            lastWork = work
            work()
            if (showProgressBar) progress.value = false
        }
    }

    fun tryAgain() {
        lastWork?.let { doWork(work = it) }
    }

}