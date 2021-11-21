package com.example.testapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.testapp.utils.PLACEHOLDER_IMAGE_URL

@ExperimentalCoilApi
@Composable
// Image using Coil Image
fun CarImage(
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    imageUrl: String
) {
    Box(contentAlignment = Alignment.Center) {
        val painter = rememberImagePainter(data = imageUrl)
        Image(
            painter = painter,
            contentScale = contentScale,
            contentDescription = null,
            modifier = modifier
        )
        if (painter.state !is ImagePainter.State.Success) {
            Image(
                painter = rememberImagePainter(data = PLACEHOLDER_IMAGE_URL),
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}