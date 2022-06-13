package com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDao: PhotoDao
}