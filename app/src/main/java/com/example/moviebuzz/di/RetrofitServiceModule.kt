package com.example.moviebuzz.di

import com.example.moviebuzz.Repository.TMDB_Service.TmdbService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitServiceModule {

    @Singleton
    @Provides
    fun getPopularMoviesServices(retrofit: Retrofit): TmdbService{

        return retrofit.create(TmdbService::class.java)
    }
}