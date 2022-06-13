package com.binar.ariefaryudisyidik.challengegoldchapter8.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.binar.ariefaryudisyidik.challengegoldchapter8.R
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response.PhotoResponse
import com.binar.ariefaryudisyidik.challengegoldchapter8.ui.theme.UnsplashTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { viewModel.isLoading.value } }
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashTheme {
                Column {
                    MainScreen()
                    PhotoList(photo = viewModel.photoListResponse)
                    viewModel.getListPhotos()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    Surface {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_unsplash_logo_horizontal),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
            )

            Text(
                stringResource(R.string.explore),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )

            var text by remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current
            TextField(
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(100.dp),
                placeholder = {
                    Text(
                        stringResource(R.string.search),
                        style = MaterialTheme.typography.body1,
                    )
                },
                singleLine = true,
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton(onClick = { text = "" }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus(true) })
            )
        }
    }
}

@Composable
fun PhotoItem(photo: PhotoResponse, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(4.dp)
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
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        itemsIndexed(items = photo) { index, item ->
            PhotoItem(photo = item, index, selectedIndex) {
                selectedIndex = it
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnsplashTheme {
        MainScreen()
    }
}