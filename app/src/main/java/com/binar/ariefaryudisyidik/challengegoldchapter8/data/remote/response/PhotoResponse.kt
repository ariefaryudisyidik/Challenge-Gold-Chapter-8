package com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response

import com.google.gson.annotations.SerializedName


data class PhotoResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("urls")
    val urls: Urls,
)

data class Urls(

    @field:SerializedName("small")
    val small: String,
)



