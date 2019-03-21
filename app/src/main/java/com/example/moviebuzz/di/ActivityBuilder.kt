package com.example.moviebuzz.di

import com.example.moviebuzz.ui.MainActivity
import com.example.moviebuzz.ui.MainActivityFragmentsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityFragmentsModule::class])
    abstract fun bindMainActivity(): MainActivity
}