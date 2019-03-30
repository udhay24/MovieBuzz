package com.example.moviebuzz.di

import com.example.moviebuzz.repository.tmdb_service.MovieService
import com.example.moviebuzz.ui.moviefragment.MovieFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [MovieFragmentModule::class])
    abstract fun contributesMovieFragment(): MovieFragment
}