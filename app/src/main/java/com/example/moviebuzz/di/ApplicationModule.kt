package com.example.moviebuzz.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule{

    @Provides
    fun providesContext(application: Application): Context = application.applicationContext



}