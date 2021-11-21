package com.example.testapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
class CarListActivity : AppCompatActivity() {

    private val viewModel by viewModels<CarListViewModel> {
        CarListViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CarListScreen(viewModel)
            }
        }
    }
}