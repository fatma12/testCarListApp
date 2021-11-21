package com.example.testapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.core.CoroutinesDispatcherProvider
import com.example.testapp.core.Injection
import com.example.testapp.data.CarListRemoteDataSource
import com.example.testapp.data.CarListRepository
import com.example.testapp.data.CarListService

class CarListViewModelFactory : ViewModelProvider.Factory {

    private val apiService by lazy {
        Injection.createService(
            service = CarListService::class.java
        )
    }

    private val carListRemoteDataSource by lazy {
        CarListRemoteDataSource(apiService)
    }

    private val carListRepository by lazy {
        CarListRepository(
            carListRemoteDataSource
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        CarListViewModel::class.java -> CarListViewModel(
            dispatcherProvider = CoroutinesDispatcherProvider(),
            carListRepository =carListRepository
        )
        else -> throw IllegalArgumentException("No ViewModel registered for $modelClass")
    } as T
}