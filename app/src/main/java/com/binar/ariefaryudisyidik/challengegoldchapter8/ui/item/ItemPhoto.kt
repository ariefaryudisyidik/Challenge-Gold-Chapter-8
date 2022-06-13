package com.binar.ariefaryudisyidik.challengegoldchapter8.ui.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response.PhotoResponse

@Composable
fun PhotoItem(photo: PhotoResponse, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(2.dp)
            .clickable { onClick(index) },
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = photo.urls.small)
                    .apply(block = fun ImageRequest.Builder.() {
                        scale(Scale.FILL)
                    }).build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoList(photo: List<PhotoResponse>) {
    var selectedIndex by remember { mutableStateOf(0) }
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        itemsIndexed(items = photo) { index, item ->
            PhotoItem(photo = item, index, selectedIndex) {
                selectedIndex = it
            }
        }
    }
}