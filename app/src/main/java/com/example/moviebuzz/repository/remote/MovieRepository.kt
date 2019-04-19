package com.example.moviebuzz.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebuzz.network.ApiResponse
import com.example.moviebuzz.network.ApiSuccessResponse
import com.example.moviebuzz.network.NetworkBoundResource
import com.example.moviebuzz.network.Resource
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.tmdb_service.MovieService
import com.example.moviebuzz.repository.model.PopularMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository constructor(private val movieService: MovieService)
    :Repository, CoroutineScope by CoroutineScope(Dispatchers.Default){

    var popularMovieResponse: Resource<PopularMovie?> = Resource.loading(null)

    fun getPopularMoviesAsync(): LiveData<Resource<PopularMovie>> {

        val popularMovie = movieService.getPopularMoviesAsync().enqueue(
            object : Callback<PopularMovie> {
                override fun onFailure(call: Call<PopularMovie>, t: Throwable) {

                    popularMovieResponse = Resource.failure(null, t.message ?: "unknown error")
                }

                override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>) {

                     popularMovieResponse = Resource.success(response.body())
                }

            }
        )
        val popularMovieLiveData = MutableLiveData(popularMovieResponse)

        return popularMovieLiveData as LiveData<Resource<PopularMovie>>
    }

    fun getNowPlayingMovies(): LiveData<Resource<NowPlayingMovie>> {

        var nowPlayingMoviesResponse: Resource<NowPlayingMovie?> = Resource.loading(null)
        val nowPlayingMovieLiveData = MutableLiveData(nowPlayingMoviesResponse)

        launch {
            val nowPlayingMovie = movieService.getNowPlayingMoviesAsync().await()
            nowPlayingMoviesResponse = Resource.success(nowPlayingMovie)
            nowPlayingMovieLiveData.postValue(nowPlayingMoviesResponse)
        }

        return nowPlayingMovieLiveData as LiveData<Resource<NowPlayingMovie>>

    }
}