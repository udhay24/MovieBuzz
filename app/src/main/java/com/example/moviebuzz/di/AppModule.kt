package com.example.moviebuzz.di

import com.example.data.repository.remote.MovieRepository
import com.example.data.repository.remote.TMDB_API_KEY
import com.example.data.repository.remote.TvRepository
import com.example.data.repository.tmdb_service.MovieService
import com.example.data.repository.tmdb_service.TvService
import com.example.moviebuzz.ui.aboutfragment.AboutViewModel
import com.example.moviebuzz.ui.moviedetail.MovieDetailViewModel
import com.example.moviebuzz.ui.moviefragment.MovieViewModel
import com.example.moviebuzz.ui.searchfragment.SearchViewModel
import com.example.moviebuzz.ui.tvfragment.TvShowsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val httpLoggingInterceptor: HttpLoggingInterceptor = {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    httpLoggingInterceptor
}.invoke()

val okHttpClient: OkHttpClient = {
    OkHttpClient.Builder()
        .addInterceptor {
            val newUrl = it.request().url
                .newBuilder()
                .addQueryParameter("api_key", TMDB_API_KEY)
                .build()

            val newRequest = it.request().newBuilder()
                .url(newUrl)
                .build()

            it.proceed(newRequest)
        }
        .addNetworkInterceptor(httpLoggingInterceptor)
        .build()
}.invoke()

val retrofit: Retrofit = {
    Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}.invoke()

val movieService: MovieService = retrofit.create(MovieService::class.java)

val movieRepository: MovieRepository = MovieRepository(movieService)

val tvService: TvService = retrofit.create(TvService::class.java)

val tvRepository: TvRepository = TvRepository(tvService)

val networkModule = module {
    single { movieRepository }
    single { tvRepository }
}

val viewModel = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { SearchViewModel() }
    viewModel { AboutViewModel() }
    viewModel { (movieId: Int) -> MovieDetailViewModel(get(), movieId) }
}