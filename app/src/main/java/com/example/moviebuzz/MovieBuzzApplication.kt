package com.example.moviebuzz

import android.app.Application
import com.example.moviebuzz.di.networkModule
import com.example.moviebuzz.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieBuzzApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieBuzzApplication)
            modules(listOf(networkModule, viewModel))
        }
        Timber.plant(Timber.DebugTree())
    }
}