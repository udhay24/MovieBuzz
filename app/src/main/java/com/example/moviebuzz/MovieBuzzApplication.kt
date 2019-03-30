package com.example.moviebuzz

import com.example.moviebuzz.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MovieBuzzApplication: DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder()
        .bindsApplication(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}