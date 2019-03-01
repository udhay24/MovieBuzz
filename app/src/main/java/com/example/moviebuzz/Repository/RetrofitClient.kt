package com.example.moviebuzz.Repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient{

    @Inject
    lateinit var okHttpClient: okhttp3.OkHttpClient

    val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

}