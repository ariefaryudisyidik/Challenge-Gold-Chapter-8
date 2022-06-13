package com.binar.ariefaryudisyidik.challengegoldchapter8.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response.PhotoResponse
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    var photoListResponse: List<PhotoResponse> by mutableStateOf(listOf())
//    var photoResponse: PhotoResponse by mutableStateOf(photoResponse)
    private var error: String by mutableStateOf("")

    init {
        splashScreen()
    }

    private fun splashScreen() {
        viewModelScope.launch {
            delay(1)
            _isLoading.value = false
        }
    }

    fun getPhotos() {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getPhotos()
                photoListResponse = response
            } catch (e: Exception) {
                error = e.message.toString()
            }
        }
    }

//    fun searchPhotos(name: String) {
//        viewModelScope.launch {
//            try {
//                val response = ApiConfig.getApiService().searchPhotos(name)
//                photoResponse = response
//            } catch (e: Exception) {
//                error = e.message.toString()
//            }
//        }
//    }
}