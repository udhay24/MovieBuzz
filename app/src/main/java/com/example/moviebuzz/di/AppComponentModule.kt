package com.example.moviebuzz.di

import android.app.Application
import android.content.Context
import com.example.moviebuzz.repository.remote.MovieRepository
import com.example.moviebuzz.repository.tmdb_service.MovieService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppComponentModule {

    @Singleton
    @Provides
    fun providesApplicationContext(application: Application): Context = application.applicationContext

}