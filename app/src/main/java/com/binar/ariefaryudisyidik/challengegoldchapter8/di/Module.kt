package com.binar.ariefaryudisyidik.challengegoldchapter8.di

import androidx.room.Room
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.PhotoRepository
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.local.room.PhotoDatabase
import com.binar.ariefaryudisyidik.challengegoldchapter8.data.remote.retrofit.ApiConfig
import com.binar.ariefaryudisyidik.challengegoldchapter8.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    factory { PhotoRepository(get(), get()) }
}

val networkModule = module {
    single { ApiConfig.getApiService() }
}

val databaseModule = module {
    factory { get<PhotoDatabase>().photoDao }
    single {
        Room.databaseBuilder(
            androidContext(),
            PhotoDatabase::class.java,
            "Photo.db"
        ).allowMainThreadQueries().build()
    }
}


