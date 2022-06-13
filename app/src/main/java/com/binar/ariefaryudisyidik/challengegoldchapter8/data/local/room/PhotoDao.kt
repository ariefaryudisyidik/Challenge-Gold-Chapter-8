package com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.entity.PhotoEntity

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo ")
    fun getFavoritePhoto(): LiveData<List<PhotoEntity>>

    @Query("SELECT count(*) FROM photo WHERE photo.id = :id")
    fun checkPhoto(id: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoritePhoto(photo: PhotoEntity)

    @Query("DELETE FROM photo WHERE photo.id = :id")
    suspend fun deleteFavoritePhoto(id: String): Int
}