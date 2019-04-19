package com.example.moviebuzz.di

import com.example.moviebuzz.repository.remote.MovieRepository
import com.example.moviebuzz.repository.tmdb_service.MovieService
import com.example.moviebuzz.repository.remote.TMDB_API_KEY
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule{

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor{

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }

    @Singleton
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
//            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitClient(okHttpClient: OkHttpClient): Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun getMovieService(retrofit: Retrofit): MovieService{

        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(movieService: MovieService): MovieRepository = MovieRepository(movieService)
}

