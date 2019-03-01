package com.example.moviebuzz.di

import com.example.moviebuzz.Repository.RetrofitClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun getOkHttpClient(): OkHttpClient = com.example.moviebuzz.Repository.OkHttpClient().getOkHttpClient()


    @Provides
    fun getRetrofitClient(): Retrofit = RetrofitClient().retrofit
}