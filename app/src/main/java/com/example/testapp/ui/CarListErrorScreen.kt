package com.example.testapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapp.R
import com.example.testapp.network.Failure
import com.example.testapp.network.ServerErrorType

@Composable
fun CarListErrorScreen(
    errorState: CarListUiState.Error,
    tryAgainAction: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val message = when (errorState.failure) {
            Failure.NetworkConnection -> {
                stringResource(id = R.string.error_no_internet_connection)
            }
            is Failure.ServerError -> {
                stringResource(
                    id = R.string.error_server_internal_error
                )
            }
            Failure.TimeoutException -> {
                stringResource(id = R.string.error_request_timeout)
            }
            else -> {
                stringResource(
                    id = R.string.error_unknown
                )
            }
        }
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body1.copy(
                DarkGray
            )
        )
        Button(
            onClick = tryAgainAction,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(
                id = R.string.try_again
            ))
        }
    }
}

@Preview(
    name = "Car List Error",
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
@Composable
fun CarListErrorPreview() {
    val errorState = CarListUiState.Error(
        failure = Failure.ServerError(ServerErrorType.UNKNOWN)
    )
    CarListErrorScreen(errorState = errorState) {}
}