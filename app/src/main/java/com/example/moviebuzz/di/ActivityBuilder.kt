package com.example.moviebuzz.di

import com.example.moviebuzz.ui.MainActivity
import com.example.moviebuzz.ui.moviefragment.MovieFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MovieFragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}