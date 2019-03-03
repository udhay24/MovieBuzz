package com.example.moviebuzz.di

import com.example.moviebuzz.Repository.TMDB_API_KEY
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Scope

@Module
class RetrofitModule{

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor{

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }

    @Provides
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{

        return OkHttpClient.Builder()
            .addInterceptor {

                val newUrl = it.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key" , TMDB_API_KEY)
                    .build()

                val newRequest = it.request().newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun getRetrofitClient(okHttpClient: OkHttpClient): Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

}

