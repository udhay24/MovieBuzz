package com.example.moviebuzz.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviebuzz.repository.model.PopularMovie
import com.example.moviebuzz.repository.network.MovieRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieViewModel
    @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    val popularMovies: MutableLiveData<Deferred<PopularMovie>> = MutableLiveData()

    init {

            popularMovies.value = movieRepository.getPopularMoviesAsync()
        }

}
