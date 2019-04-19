package com.example.moviebuzz.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebuzz.network.*
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import com.example.moviebuzz.repository.tmdb_service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository constructor(private val movieService: MovieService)
    :Repository, CoroutineScope by CoroutineScope(Dispatchers.Default){


    fun getPopularMoviesAsync(): LiveData<Resource<PopularMovie>> {
        var popularMovieResponse: Resource<PopularMovie?> = Resource.loading(null)
        val popularMovieLiveData = MutableLiveData(popularMovieResponse)

        val popularMovie = movieService.getPopularMoviesAsync().enqueue(
            object : Callback<PopularMovie> {
                override fun onFailure(call: Call<PopularMovie>, t: Throwable) {
                    popularMovieResponse = Resource.failure(null, t.message ?: "unknown error")
                    popularMovieLiveData.postValue(popularMovieResponse)
                }

                override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>) {
                    popularMovieResponse = Resource.success(response.body())
                    popularMovieLiveData.postValue(popularMovieResponse)
                }

            }
        )

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

    fun fetchPopularMovies(): LiveData<Resource<PopularMovie>> {
        return object : NetworkBoundResource<PopularMovie, PopularMovie>() {

            override fun networkCall(): LiveData<ApiResponse<PopularMovie>> {
                var apiResponse: ApiResponse<PopularMovie> = ApiEmptyResponse()

                movieService.getPopularMoviesAsync().enqueue(
                    object : Callback<PopularMovie> {

                        override fun onFailure(call: Call<PopularMovie>, t: Throwable) {
                            apiResponse = ApiErrorResponse(t.message ?: "Unknown error")
                        }

                        override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>) {
                            apiResponse = ApiResponse.create(response)
                        }
                    }
                )
                return MutableLiveData<ApiResponse<PopularMovie>>(apiResponse)
            }

            override fun convertToResultType(requestType: PopularMovie): PopularMovie = requestType

        }.asLiveData()
    }
}