package com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.retrofit

import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response.PhotoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<PhotoResponse>
}