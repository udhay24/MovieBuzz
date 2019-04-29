package com.example.moviebuzz.ui.moviefragment

import androidx.lifecycle.ViewModel
import com.example.moviebuzz.repository.remote.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MovieViewModel(movieRepository: MovieRepository)
    : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Default){

    val popularMovies = movieRepository.fetchPopularMovies()
    val nowPlayingMovies = movieRepository.fetchNowPlayingMovies()
    val latestMovie = movieRepository.fetchLatestMovie()
    val topRatedMovies = movieRepository.fetchTopRatedMovies()
}
