package com.binar.ariefaryudisyidik.challengegoldchapter8.mapper

import com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.entity.PhotoEntity
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.response.PhotoResponse

object Mapper {
    private fun mapResponseToEntity(photo: PhotoResponse) = PhotoEntity(
        photo.id,
        photo.urls.small
    )

    fun mapResponsesToEntities(input: List<PhotoResponse>): List<PhotoEntity> {
        return input.map { mapResponseToEntity(it) }
    }
}