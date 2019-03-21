package com.example.moviebuzz.ui

import com.example.moviebuzz.ui.moviefragment.MovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @ContributesAndroidInjector
    abstract fun bindMovieFragment(): MovieFragment
}