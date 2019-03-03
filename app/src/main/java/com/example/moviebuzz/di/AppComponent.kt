package com.example.moviebuzz.di

import com.example.moviebuzz.MainActivity
import com.example.moviebuzz.Repository.TMDB_Service.MovieService
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}