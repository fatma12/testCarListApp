package com.example.testapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.core.CoroutinesDispatcherProvider
import com.example.testapp.data.CarListRepository
import com.example.testapp.model.CarItemUiModel
import com.example.testapp.model.CarDto
import com.example.testapp.model.toUiModel
import com.example.testapp.network.Failure
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class CarListUiState {
    object Loading : CarListUiState()
    object Empty : CarListUiState()
    data class Success(val carList: List<CarItemUiModel>) : CarListUiState()
    data class Error(val failure: Failure) : CarListUiState()
}

sealed class CarListUiEvents {
    object RetryGetCarList : CarListUiEvents()
}

class CarListViewModel(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    val carListRepository: CarListRepository
) : ViewModel() {

    // Ui States
    private val _uiState = MutableStateFlow<CarListUiState>(CarListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    // Ui Events
    private val _uiEvents = MutableSharedFlow<CarListUiEvents>()

    init {
        subscribeToUiEvents()
        getCarList()
    }

    fun handleUiEvent(event: CarListUiEvents) {
        viewModelScope.launch {
            _uiEvents.emit(event)
        }
    }

    private fun subscribeToUiEvents() {
        _uiEvents.onEach { event ->
            // new ui events
            when (event) {
                CarListUiEvents.RetryGetCarList -> {
                    getCarList()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCarList() {
        _uiState.value = CarListUiState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            val result = carListRepository.getCarList()

            // changing back to the main thread
            withContext(dispatcherProvider.main) {
                result.fold(::handleGetCarListFailure, ::handleGetCarListSuccess)
            }
        }
    }

    private fun handleGetCarListFailure(failure: Failure) {
        _uiState.value = CarListUiState.Error(failure = failure)
    }

    private fun handleGetCarListSuccess(result: List<CarDto>) {
        if (result.isEmpty()) {
            _uiState.value = CarListUiState.Empty
        } else {
            val carList = result.map {
                it.toUiModel()
            }
            _uiState.value = CarListUiState.Success(
                carList = carList
            )
        }
    }
}