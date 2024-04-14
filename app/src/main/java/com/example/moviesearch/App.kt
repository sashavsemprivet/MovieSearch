package com.example.moviesearch

import android.app.Application
import com.example.moviesearch.di.ApplicationComponent
import com.example.moviesearch.di.DaggerApplicationComponent
import com.example.moviesearch.di.modules.NetworkModule

class App : Application() {

    val appComponent: ApplicationComponent? = DaggerApplicationComponent.builder()
        .networkModule(NetworkModule())
        .build()
}