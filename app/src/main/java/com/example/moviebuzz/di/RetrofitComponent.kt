package com.example.moviebuzz.di

import com.example.moviebuzz.MainActivity
import com.example.moviebuzz.Repository.TMDB_Service.TmdbService
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class , RetrofitServiceModule::class])
interface RetrofitComponent {

    fun getRetrofitClient(): Retrofit

    fun inject(mainActivity: MainActivity)

    fun getTmdbService(): TmdbService
}