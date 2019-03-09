package com.example.moviebuzz.ui.moviefragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieFragmentModule {

    @ContributesAndroidInjector
    abstract fun bindMovieFragment(): MovieFragment
}