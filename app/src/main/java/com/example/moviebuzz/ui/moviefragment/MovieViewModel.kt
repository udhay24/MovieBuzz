package com.example.moviebuzz.ui.moviefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviebuzz.network.BaseUrl
import com.example.moviebuzz.network.NetworkStatus
import com.example.moviebuzz.repository.remote.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MovieViewModel(movieRepository: MovieRepository)
    : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Default){

    private val popularMovies = movieRepository.fetchPopularMovies()
    private val nowPlayingMovies = movieRepository.fetchNowPlayingMovies()
    private val latestMovie = movieRepository.fetchLatestMovie()
    private val topRatedMovies = movieRepository.fetchTopRatedMovies()
    private val upcomingMovies = movieRepository.fetchUpComingMovies()

    val popularMoviePosterList: LiveData<List<String>> = Transformations.map(popularMovies) {
        when (it.status) {
            NetworkStatus.SUCCESS -> {
                it.data?.results?.map { result ->
                    BaseUrl.getPosterPath(result.poster_path)
                }
            }
            else -> emptyList()
        }
    }

    val nowPlayingMoviePosterList: LiveData<List<String>> = Transformations.map(nowPlayingMovies) {
        when (it.status) {
            NetworkStatus.SUCCESS -> {
                it.data?.results?.map { result ->
                    BaseUrl.getPosterPath(result.poster_path)
                }
            }
            else -> emptyList()
        }
    }

    val topRatedMoviesPosterList: LiveData<List<String>> = Transformations.map(topRatedMovies) {
        when (it.status) {
            NetworkStatus.SUCCESS -> {
                it.data?.map { result ->
                    BaseUrl.getPosterPath(result.poster_path ?: "")
                }
            }
            else -> emptyList()
        }
    }

    val upComingMoviesPosterList: LiveData<List<String>> = Transformations.map(upcomingMovies) {
        when (it.status) {
            NetworkStatus.SUCCESS -> {
                it.data?.map { result ->
                    BaseUrl.getPosterPath(result.poster_path)
                }
            }
            else -> emptyList()
        }
    }

    val latestMovieName = Transformations.map(latestMovie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> it.data?.title ?: "No Name"
            else -> "No Name"
        }
    }
    val latestMoviePosterUrl = Transformations.map(latestMovie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> BaseUrl.LATEST_MOVIE_POSTER_URL + it.data?.poster_path
            else -> ""
        }
    }
}
