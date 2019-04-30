package com.example.moviebuzz.repository.model.movie

data class LatestMovies(
    val adult: Boolean,
    val backdrop_path: Any,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: Any,
    val id: Int,
    val imdb_id: Any,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Int,
    val poster_path: Any,
    val production_companies: List<Any>,
    val production_countries: List<Any>,
    val release_date: String,
    val revenue: Int,
    val runtime: Any,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Int,
    val vote_count: Int
) {
    data class Genre(
        val id: Int,
        val name: String
    )

    data class SpokenLanguage(
        val iso_639_1: String,
        val name: String
    )
}