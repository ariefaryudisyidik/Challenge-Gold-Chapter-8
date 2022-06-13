package com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(
    @field:PrimaryKey
    val id: String,
    val url: String
)