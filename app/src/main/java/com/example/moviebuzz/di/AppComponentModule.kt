package com.example.moviebuzz.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppComponentModule {

    @Singleton
    @Provides
    fun providesApplicationContext(application: Application): Context = application.applicationContext

}