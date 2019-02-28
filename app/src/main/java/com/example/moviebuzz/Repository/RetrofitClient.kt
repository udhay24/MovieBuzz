package com.example.moviebuzz.Repository

import com.example.moviebuzz.Repository.OkHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient{

    fun getRetrofitClient(): Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().getOkHttpClient())
            .build()

    }
}