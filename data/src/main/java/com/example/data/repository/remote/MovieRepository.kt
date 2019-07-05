package com.example.data.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.network.ApiEmptyResponse
import com.example.data.network.ApiErrorResponse
import com.example.data.network.ApiResponse
import com.example.data.network.ApiSuccessResponse
import com.example.data.network.NetworkBoundResource
import com.example.data.network.Resource
import com.example.data.repository.model.movie.LatestMovies
import com.example.data.repository.model.movie.Movie
import com.example.data.repository.model.movie.MovieReview
import com.example.data.repository.model.movie.NowPlayingMovies
import com.example.data.repository.model.movie.PopularMovies
import com.example.data.repository.model.movie.SimilarMovies
import com.example.data.repository.model.movie.TopRatedMovies
import com.example.data.repository.model.movie.UpComingMovies
import com.example.data.repository.tmdb_service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository constructor(private val movieService: MovieService) : Repository,
    CoroutineScope by CoroutineScope(Dispatchers.IO) {

    fun fetchNowPlayingMovies(): LiveData<Resource<NowPlayingMovies>> {
        return object : NetworkBoundResource<NowPlayingMovies, NowPlayingMovies>() {

            override fun networkCall(): LiveData<ApiResponse<NowPlayingMovies>> {
                val nowPlayingLiveData = MutableLiveData<ApiResponse<NowPlayingMovies>>(ApiEmptyResponse())
                launch {
                    try {
                        val networkResponse = movieService.getNowPlayingMovies()
                        if (networkResponse.isSuccessful and (networkResponse.body() != null)) {
                            nowPlayingLiveData.postValue(
                                ApiSuccessResponse(networkResponse.body()!!, "")
                            )
                        }
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
                        val networkResponse = movieService.getPopularMovies()
                        if (networkResponse.isSuccessful and (networkResponse.body() != null)) {
                            popularMovieLiveData.postValue(
                                ApiSuccessResponse(networkResponse.body()!!, "")
                            )
                        }
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
                    val response = movieService.getLatestMovies()
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
                        val networkResponse = movieService.getTopRatedMovies()
                        if (networkResponse.isSuccessful and (networkResponse.body() != null)) {
                            topRatedMovieLiveData.postValue(
                                ApiSuccessResponse(networkResponse.body()!!, "")
                            )
                        }
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
                        val networkResponse = movieService.getUpComingMovies()
                        if (networkResponse.isSuccessful and (networkResponse.body() != null)) {
                            mutableLiveData.postValue(
                                ApiSuccessResponse(networkResponse.body()!!, "")
                            )
                        }
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
                    val networkResponse = movieService.getMovieDetailFromId(movieId)
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

    fun fetchMovieReviews(movieId: Int): LiveData<Resource<MovieReview>> {
        return object : NetworkBoundResource<MovieReview, MovieReview>() {
            override fun networkCall(): LiveData<ApiResponse<MovieReview>> {
                val result = MutableLiveData<ApiResponse<MovieReview>>(ApiEmptyResponse())
                launch {
                    val networkResponse = movieService.getMovieReviews(movieId)
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

            override fun convertToResultType(requestType: MovieReview): MovieReview = requestType

        }.asLiveData()
    }

    fun fetchSimilarMovies(movieId: Int): LiveData<Resource<SimilarMovies>> {
        return object : NetworkBoundResource<SimilarMovies, SimilarMovies>() {
            override fun networkCall(): LiveData<ApiResponse<SimilarMovies>> {
                val result = MutableLiveData<ApiResponse<SimilarMovies>>(ApiEmptyResponse())
                launch {
                    val networkResponse = movieService.getSimilarMovies(movieId)
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

            override fun convertToResultType(requestType: SimilarMovies): SimilarMovies = requestType

        }.asLiveData()
    }
}