package com.example.testapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.testapp.R
import com.example.testapp.model.CarItemUiModel
import com.example.testapp.utils.PLACEHOLDER_IMAGE_URL

@ExperimentalCoilApi
@Composable
fun CarListScreen(viewModel: CarListViewModel) {
    when (val uiState = viewModel.uiState.collectAsState().value) {
        CarListUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        CarListUiState.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.empty_list_text),
                    style = MaterialTheme.typography.h6.copy(color = Color.Black)
                )
            }
        }
        is CarListUiState.Success -> {
            CarList(modifier = Modifier, carList = uiState.carList)
        }
        is CarListUiState.Error -> {
            CarListErrorScreen(errorState = uiState) {
                viewModel.handleUiEvent(
                    CarListUiEvents.RetryGetCarList
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CarList(
    modifier: Modifier,
    carList: List<CarItemUiModel>
) {
    LazyColumn(modifier = modifier) {
        items(carList) {
            CarItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ),
                item = it
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CarItem(
    modifier: Modifier,
    item: CarItemUiModel
) {
    CarCard(
        modifier = modifier,
        elevation = 2.dp,
        shape = small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 0.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CarImage(
                modifier = Modifier
                    .size(70.dp),
                imageUrl = item.image ?: PLACEHOLDER_IMAGE_URL
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 12.dp,
                        end = 12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6.copy(color = Color(0xFF444444))
                )
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.body2.copy(color = Color(0xFF767676))
                )
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun CarItemPreview(@PreviewParameter(CarItemProvider::class) item: CarItemUiModel) {
    MaterialTheme {
        CarItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            item = item
        )
    }
}

class CarItemProvider : PreviewParameterProvider<CarItemUiModel> {
    override val values: Sequence<CarItemUiModel>
        get() = sequenceOf(
            CarItemUiModel(
                image = "https://afterpay-mobile-interview.s3.amazonaws.com/images/honda_civic_1.jpg",
                title = "2019 Honda Civic",
                price = "$17500"
            ),
            CarItemUiModel(
                image = "https://afterpay-mobile-interview.s3.amazonaws.com/images/honda_civic_1.jpg",
                title = "2019 Honda Civic",
                price = "$17500"
            )
        )
}