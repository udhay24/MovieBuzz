package com.example.moviebuzz.repository.model.movie

data class MovieReview(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) {
    data class Result(
        val author: String,
        val content: String,
        val id: String,
        val url: String
    )
}