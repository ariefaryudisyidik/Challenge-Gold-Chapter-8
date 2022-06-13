package com.binar.ariefaryudisyidik.challengegoldchapter8.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.binar.ariefaryudisyidik.challengegoldchapter8.R
import com.binar.ariefaryudisyidik.challengegoldchapter8.ui.item.PhotoList
import com.binar.ariefaryudisyidik.challengegoldchapter8.ui.theme.Grey
import com.binar.ariefaryudisyidik.challengegoldchapter8.ui.theme.UnsplashTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { viewModel.isLoading.value } }
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashTheme {
                Column {
                    MainScreen(MainViewModel())
                    PhotoList(photo = viewModel.photoListResponse)
                    viewModel.getPhotos()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
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
            val context = LocalContext.current

            TextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                shape = RoundedCornerShape(100.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Grey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        stringResource(R.string.search),
                        style = MaterialTheme.typography.body1,
                    )
                },
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
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .background(Grey, CircleShape),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus(true);
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
//                    viewModel.searchPhotos(text)
                })
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnsplashTheme {
        MainScreen(MainViewModel())
    }
}