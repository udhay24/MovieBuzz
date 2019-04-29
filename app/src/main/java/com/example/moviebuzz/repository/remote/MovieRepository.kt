package com.example.moviebuzz.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebuzz.network.*
import com.example.moviebuzz.repository.model.movie.LatestMovie
import com.example.moviebuzz.repository.model.movie.NowPlayingMovie
import com.example.moviebuzz.repository.model.movie.PopularMovie
import com.example.moviebuzz.repository.model.movie.TopRatedMovies
import com.example.moviebuzz.repository.tmdb_service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository constructor(private val movieService: MovieService)
    :Repository, CoroutineScope by CoroutineScope(Dispatchers.Default){

    fun fetchNowPlayingMovies(): LiveData<Resource<NowPlayingMovie>> {
        return object : NetworkBoundResource<NowPlayingMovie, NowPlayingMovie>() {

            override fun networkCall(): LiveData<ApiResponse<NowPlayingMovie>> {
                val nowPlayingLiveData = MutableLiveData<ApiResponse<NowPlayingMovie>>(ApiEmptyResponse())
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

            override fun convertToResultType(requestType: NowPlayingMovie): NowPlayingMovie = requestType
        }.asLiveData()
    }

    fun fetchPopularMovies(): LiveData<Resource<PopularMovie>> {
        return object : NetworkBoundResource<PopularMovie, PopularMovie>() {

            override fun networkCall(): LiveData<ApiResponse<PopularMovie>> {
                val popularMovieLiveData = MutableLiveData<ApiResponse<PopularMovie>>(ApiEmptyResponse())
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

            override fun convertToResultType(requestType: PopularMovie): PopularMovie = requestType

        }.asLiveData()
    }

    fun fetchLatestMovie(): LiveData<Resource<LatestMovie>> {
        return object : NetworkBoundResource<LatestMovie, LatestMovie>() {

            override fun networkCall(): LiveData<ApiResponse<LatestMovie>> {
                val latestMovieLiveData = MutableLiveData<ApiResponse<LatestMovie>>(ApiEmptyResponse())
                launch {
                    try {
                        val latestMovie = movieService.getLatestMoviesAsync().await()
                        latestMovieLiveData.postValue(
                            ApiSuccessResponse(latestMovie, "")
                        )
                    } catch (exception: Exception) {
                        latestMovieLiveData.postValue(ApiErrorResponse(exception.localizedMessage))
                    }
                }
                return latestMovieLiveData
            }

            override fun convertToResultType(requestType: LatestMovie): LatestMovie = requestType

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
}