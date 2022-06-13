package com.binar.ariefaryudisyidik.challengegoldchapter8.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.PhotoRepository
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response.PhotoResponse
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

//    private val _photos = MutableLiveData<Result<List<PhotoEntity>>>()
//    val photos: LiveData<Result<List<PhotoEntity>>> = _photos

    var photoListResponse: List<PhotoResponse> by mutableStateOf(listOf())
    var error: String by mutableStateOf("")


    init {
        viewModelScope.launch {
            delay(1)
            _isLoading.value = false
        }
    }

    fun getListPhotos() {
//        viewModelScope.launch {
//            photoRepository.getPhotos().asFlow().collect() {
//                _photos.postValue(it)
//            }
//        }
        viewModelScope.launch {
            try {
                val photoList = ApiConfig.getApiService().getPhotos()
                photoListResponse = photoList
            } catch (e: Exception) {
                error = e.message.toString()
            }
        }
    }
}