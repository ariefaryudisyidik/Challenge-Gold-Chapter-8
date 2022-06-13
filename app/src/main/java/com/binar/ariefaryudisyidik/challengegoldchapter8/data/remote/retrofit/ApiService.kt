package com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.retrofit

import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<PhotoResponse>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") name: String
    ): PhotoResponse
}