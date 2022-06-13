package com.binar.ariefaryudisyidik.challengegoldchapter8

import android.app.Application
import com.binar.ariefaryudisyidik.challengegoldchapter8.di.databaseModule
import com.binar.ariefaryudisyidik.challengegoldchapter8.di.networkModule
import com.binar.ariefaryudisyidik.challengegoldchapter8.di.repositoryModule
import com.binar.ariefaryudisyidik.challengegoldchapter8.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule,
                    databaseModule
                )
            )
        }
    }
}