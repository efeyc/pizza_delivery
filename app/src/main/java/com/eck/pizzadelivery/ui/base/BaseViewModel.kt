package com.eck.pizzadelivery.ui.base

import androidx.lifecycle.ViewModel
import com.eck.pizzadelivery.network.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(coroutineContext: CoroutineContext) : ViewModel() {

    private val parentJob = Job()
    private val scope = CoroutineScope(parentJob + coroutineContext)

    private val _baseState = MutableStateFlow<BaseState>(BaseState.None)
    var baseState = _baseState.asStateFlow()

    private var progressCount = 0

    open fun showProgress() {
        progressCount++
        _baseState.value = BaseState.Loading
    }

    open fun hideProgress() {
        progressCount--
        if (progressCount < 0) {
            progressCount = 0
        }

        if (progressCount == 0) {
            clearState()
        }
    }

    open fun clearState() {
        _baseState.value = BaseState.None
    }

    fun <T> call(
        m: suspend () -> NetworkState<T>,
        successMethod: ((T) -> Unit)? = null,
        errorMethod: ((String, Int) -> Unit)? = null,
        progress: Boolean = true
    ) {
        scope.launch {
            if (progress) {
                showProgress()
            }

            val response = try {
                m.invoke()
            } catch (e: Exception) {
                NetworkState.Error(e.message ?: "", -1)
            }

            if (progress) {
                hideProgress()
            }

            when (response) {
                is NetworkState.Success -> {
                    successMethod?.invoke(response.data)
                }

                is NetworkState.Error -> {
                    if (errorMethod == null) {
                        _baseState.value = BaseState.Error(response.error)
                    } else {
                        errorMethod.invoke(response.error, response.code)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    sealed interface BaseState {
        object None : BaseState
        object Loading : BaseState
        data class Error(val error: String) : BaseState
    }
}