package com.example.moviebuzz.Repository

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OkHttpClient {

    @Inject
    fun getOkHttpClient(): OkHttpClient{

        return OkHttpClient.Builder()

            //Add Application interceptor to add tmdb api key to all the request
            .addInterceptor {

                //build a new ok http Url with api key
                val newUrl = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key" , TMDB_API_KEY)
                    .build()

                val newRequest = it.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .build()
    }
}