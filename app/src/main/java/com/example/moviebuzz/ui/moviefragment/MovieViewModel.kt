package com.example.moviebuzz.ui.moviefragment

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.data.network.BaseUrl
import com.example.data.network.NetworkStatus
import com.example.data.repository.remote.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MovieViewModel(movieRepository: MovieRepository)
    : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Default){

    val popularMovies = movieRepository.fetchPopularMovies()
    val nowPlayingMovies = movieRepository.fetchNowPlayingMovies()
    val latestMovie = movieRepository.fetchLatestMovie()
    val topRatedMovies = movieRepository.fetchTopRatedMovies()
    val upcomingMovies = movieRepository.fetchUpComingMovies()

    val latestMoviePosterUrl = Transformations.map(latestMovie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> BaseUrl.getPosterPath(it.data!!.poster_path)
            else -> ""
        }
    }

    val latestMovieName = Transformations.map(latestMovie) {
        when (it.status) {
            NetworkStatus.SUCCESS -> it.data?.title
            else -> ""
        }
    }
}
