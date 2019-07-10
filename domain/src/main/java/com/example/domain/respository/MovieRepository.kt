package com.example.domain.respository

import com.example.domain.entities.Failure
import com.example.domain.entities.OneOf
import com.example.domain.entities.model.movie.NowPlayingMovies

interface MovieRepository {

    fun fetchNowPlayingMovies(): OneOf<NowPlayingMovies, Failure>
}