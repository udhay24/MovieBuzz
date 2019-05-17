package com.example.moviebuzz.ui.moviedetail

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviebuzz.network.NetworkStatus
import com.example.moviebuzz.repository.remote.MovieRepository

class MovieDetailViewModel(movieRepository: MovieRepository, val movieId: Int) : ViewModel() {

    val movie = movieRepository.fetchMovieById(movieId)

    val movieName = Transformations.map(movie) {
        when (it.status) {
            NetworkStatus.FAILURE -> ""
            NetworkStatus.LOADING -> "Loading"
            NetworkStatus.SUCCESS -> it.data?.title
        }
    }

    val name = "Nothing"
}
