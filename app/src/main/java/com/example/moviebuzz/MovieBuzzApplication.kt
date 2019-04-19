package com.example.moviebuzz

import com.example.moviebuzz.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MovieBuzzApplication: DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder()
        .bindsApplication(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}