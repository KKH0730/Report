package com.example.base.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel : ViewModel() {

    internal val _messageFlow = MutableSharedFlow<String>()
    val messageFlow get() = _messageFlow.asSharedFlow()

    internal val _messageWithLongDurationFlow = MutableSharedFlow<String>()
    val messageWithLongDurationFlow get() = _messageWithLongDurationFlow.asSharedFlow()

    private val _loadingState =  MutableStateFlow(false)
    val loadingState get() = _loadingState.asStateFlow()


    val networkingFlow =  MutableStateFlow(false)

    fun showLoading(isShow: Boolean) {
        _loadingState.value = isShow
    }
}