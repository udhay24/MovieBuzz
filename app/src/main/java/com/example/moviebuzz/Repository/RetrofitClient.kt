package com.example.moviebuzz.Repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.getOkHttpClient())
            .build()
    }

}