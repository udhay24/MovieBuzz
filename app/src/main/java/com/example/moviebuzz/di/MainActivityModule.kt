package com.example.moviebuzz.di

import com.example.moviebuzz.repository.tmdb_service.MovieService
import com.example.moviebuzz.ui.moviefragment.MovieFragment
import com.example.moviebuzz.ui.searchfragment.SearchFragment
import com.example.moviebuzz.ui.tvfragment.TvShowsFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contribuesTvShowFragment(): TvShowsFragment

    @ContributesAndroidInjector
    abstract fun contributesSearchFragment(): SearchFragment
}