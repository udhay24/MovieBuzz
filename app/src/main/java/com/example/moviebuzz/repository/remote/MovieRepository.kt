package com.example.moviebuzz.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebuzz.network.ApiEmptyResponse
import com.example.moviebuzz.network.ApiErrorResponse
import com.example.moviebuzz.network.ApiResponse
import com.example.moviebuzz.network.ApiSuccessResponse
import com.example.moviebuzz.network.NetworkBoundResource
import com.example.moviebuzz.network.Resource
import com.example.moviebuzz.repository.model.movie.LatestMovies
import com.example.moviebuzz.repository.model.movie.Movie
import com.example.moviebuzz.repository.model.movie.NowPlayingMovies
import com.example.moviebuzz.repository.model.movie.PopularMovies
import com.example.moviebuzz.repository.model.movie.TopRatedMovies
import com.example.moviebuzz.repository.model.movie.UpComingMovies
import com.example.moviebuzz.repository.tmdb_service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieRepository constructor(private val movieService: MovieService)
    :Repository, CoroutineScope by CoroutineScope(Dispatchers.Default){

    fun fetchNowPlayingMovies(): LiveData<Resource<NowPlayingMovies>> {
        return object : NetworkBoundResource<NowPlayingMovies, NowPlayingMovies>() {

            override fun networkCall(): LiveData<ApiResponse<NowPlayingMovies>> {
                val nowPlayingLiveData = MutableLiveData<ApiResponse<NowPlayingMovies>>(ApiEmptyResponse())
                launch {
                    try {
                        val nowPlayingMovie = movieService.getNowPlayingMoviesAsync().await()
                        nowPlayingLiveData.postValue(
                            ApiSuccessResponse(nowPlayingMovie, "")
                        )
                    } catch (exception: Exception) {
                        nowPlayingLiveData.postValue(ApiErrorResponse(exception.localizedMessage))
                    }
                }
                return nowPlayingLiveData
            }

            override fun convertToResultType(requestType: NowPlayingMovies): NowPlayingMovies = requestType
        }.asLiveData()
    }

    fun fetchPopularMovies(): LiveData<Resource<PopularMovies>> {
        return object : NetworkBoundResource<PopularMovies, PopularMovies>() {

            override fun networkCall(): LiveData<ApiResponse<PopularMovies>> {
                val popularMovieLiveData = MutableLiveData<ApiResponse<PopularMovies>>(ApiEmptyResponse())
                launch {
                    try {
                        val popularMovie = movieService.getPopularMoviesAsync().await()
                        popularMovieLiveData.postValue(
                            ApiSuccessResponse(popularMovie, "")
                        )
                    } catch (exception: Exception) {
                        popularMovieLiveData.postValue(ApiErrorResponse(exception.localizedMessage))
                    }
                }
                return popularMovieLiveData
            }

            override fun convertToResultType(requestType: PopularMovies): PopularMovies = requestType

        }.asLiveData()
    }

    fun fetchLatestMovie(): LiveData<Resource<LatestMovies>> {
        return object : NetworkBoundResource<LatestMovies, LatestMovies>() {

            override fun networkCall(): LiveData<ApiResponse<LatestMovies>> {
                val latestMovieLiveData = MutableLiveData<ApiResponse<LatestMovies>>(ApiEmptyResponse())
                launch {
                    val response = movieService.getLatestMoviesAsync().await()
                    Timber.v(response.raw().request().url().toString())
                    if (response.isSuccessful) {
                        latestMovieLiveData.postValue(
                            ApiSuccessResponse(response.body()!!, "")
                        )
                    } else {
                        latestMovieLiveData.postValue(ApiErrorResponse(response.errorBody().toString()))
                    }
                }
                return latestMovieLiveData
            }

            override fun convertToResultType(requestType: LatestMovies): LatestMovies = requestType

        }.asLiveData()
    }

    fun fetchTopRatedMovies(): LiveData<Resource<List<TopRatedMovies.Result>>> {
        return object : NetworkBoundResource<List<TopRatedMovies.Result>, TopRatedMovies>() {

            override fun networkCall(): LiveData<ApiResponse<TopRatedMovies>> {
                val topRatedMovieLiveData = MutableLiveData<ApiResponse<TopRatedMovies>>(ApiEmptyResponse())
                launch {
                    try {
                        val topRatedMovies = movieService.getTopRatedMoviesAsync().await()
                        topRatedMovieLiveData.postValue(
                            ApiSuccessResponse(topRatedMovies, "")
                        )
                    } catch (exception: Exception) {
                        topRatedMovieLiveData.postValue(
                            ApiErrorResponse(exception.localizedMessage)
                        )
                    }
                }
                return topRatedMovieLiveData
            }

            override fun convertToResultType(requestType: TopRatedMovies): List<TopRatedMovies.Result> =
                requestType.results

        }.asLiveData()
    }

    fun fetchUpComingMovies(): LiveData<Resource<List<UpComingMovies.Result>>> {
        return object : NetworkBoundResource<List<UpComingMovies.Result>, UpComingMovies>() {
            override fun networkCall(): LiveData<ApiResponse<UpComingMovies>> {
                val mutableLiveData = MutableLiveData<ApiResponse<UpComingMovies>>(ApiEmptyResponse())
                launch {
                    try {
                        val upComingMovies = movieService.getUpComingMoviesAsync().await()
                        mutableLiveData.postValue(
                            ApiSuccessResponse(upComingMovies, "")
                        )
                    } catch (exception: Exception) {
                        mutableLiveData.postValue(ApiErrorResponse(exception.localizedMessage))
                    }
                }
                return mutableLiveData
            }

            override fun convertToResultType(requestType: UpComingMovies): List<UpComingMovies.Result> =
                requestType.results
        }.asLiveData()
    }

    fun fetchMovieById(movieId: Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, Movie>() {
            override fun networkCall(): LiveData<ApiResponse<Movie>> {
                val result = MutableLiveData<ApiResponse<Movie>>(ApiEmptyResponse())
                launch {
                    val networkResponse = movieService.getMovieDetailFromIdAsync(movieId).await()
                    if (networkResponse.isSuccessful and (networkResponse.body() != null)) {
                        result.postValue(
                            ApiSuccessResponse(networkResponse.body()!!, "")
                        )
                    } else {
                        result.postValue(
                            ApiErrorResponse(networkResponse.errorBody().toString())
                        )
                    }
                }
                return result
            }

            override fun convertToResultType(requestType: Movie): Movie = requestType
        }.asLiveData()
    }
}