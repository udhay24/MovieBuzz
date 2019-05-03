package com.example.moviebuzz.ui.moviedetail

import androidx.lifecycle.ViewModel
import com.example.moviebuzz.repository.remote.MovieRepository

class MovieDetailViewModel(movieRepository: MovieRepository, val movieId: Int) : ViewModel() {

    val movie = movieRepository.fetchMovieById(movieId)
}
