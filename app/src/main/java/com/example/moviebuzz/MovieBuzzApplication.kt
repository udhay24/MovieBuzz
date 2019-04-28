package com.example.moviebuzz

import com.example.moviebuzz.di.DaggerAppComponent
import com.example.moviebuzz.di.networkModule
import com.example.moviebuzz.di.viewModel
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieBuzzApplication: DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder()
        .bindsApplication(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieBuzzApplication)
            modules(listOf(networkModule, viewModel))
        }
        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}