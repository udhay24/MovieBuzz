package com.example.moviebuzz.network

object BaseUrl {
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w200"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
    private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"
    val LATEST_MOVIE_POSTER_URL = "https://image.tmdb.org/t/p/w300"

    fun getPosterPath(posterPath: String): String {
        return BASE_POSTER_PATH + posterPath
    }

    fun getBackdropPath(backdropPath: String): String {
        return BASE_BACKDROP_PATH + backdropPath
    }

    fun getYoutubeVideoPath(videoPath: String): String {
        return YOUTUBE_VIDEO_URL + videoPath
    }

    fun getYoutubeThumbnailPath(thumbnailPath: String): String {
        return "$YOUTUBE_THUMBNAIL_URL + $thumbnailPath + /default.jpg"
    }
}