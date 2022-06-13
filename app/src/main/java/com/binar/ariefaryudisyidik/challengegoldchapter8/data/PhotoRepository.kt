package com.binar.ariefaryudisyidik.challengegoldchapter8.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.entity.PhotoEntity
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.room.PhotoDao
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.retrofit.ApiService
import com.binar.ariefaryudisyidik.challengegoldchapter8.mapper.Mapper

class PhotoRepository(
    private val apiService: ApiService,
    private val photoDao: PhotoDao
) {
    fun getPhotos(): LiveData<Result<List<PhotoEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getPhotos()
            emit(Result.Success(Mapper.mapResponsesToEntities(response)))
        } catch (e: Exception) {
            Log.e(TAG, "searchUser: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        const val TAG = "PhotoRepository"
    }
}