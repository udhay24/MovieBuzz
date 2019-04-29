package com.example.moviebuzz.ui.moviefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.repository.model.movie.NowPlayingMovie
import com.example.moviebuzz.repository.model.movie.PopularMovie
import com.example.moviebuzz.repository.model.movie.TopRatedMovies
import com.squareup.picasso.Picasso

class PopularMoviesAdapter(private val context: Context?,
                           private val popularMovie: PopularMovie
)
    : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.single_image_layout, parent, false)

        return PopularMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {

        return popularMovie.results.count()
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        val url = "https://image.tmdb.org/t/p/w300${popularMovie.results[position].poster_path}"

        Picasso.get()
            .load(url)
            .into(holder.imageView)
    }

    class PopularMoviesViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        val imageView: ImageView = view.findViewById(R.id.movie_poster)
    }
}

class NowPlayingMoviesAdapter(private val context: Context?,
                              private val nowPlayingMovie: NowPlayingMovie
)
    :RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingMovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.single_image_layout, parent, false)

        return NowPlayingMovieViewHolder(view)
    }

    override fun getItemCount(): Int {

        return nowPlayingMovie.results.count()
    }

    override fun onBindViewHolder(holder: NowPlayingMovieViewHolder, position: Int) {

        val posterUrl = "https://image.tmdb.org/t/p/w300${nowPlayingMovie.results[position].poster_path}"

        //set poster image
        Picasso.get()
            .load(posterUrl)
            .into(holder.moviePoster)

    }

    class NowPlayingMovieViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        val moviePoster: ImageView = view.findViewById(R.id.movie_poster)
    }
}

class TopRatedMoviesAdapter(private val results: List<TopRatedMovies.Result>) :
    RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_image_layout, parent, false)

        return TopRatedMoviesViewHolder(view)
    }


    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        holder.setImage(results[position].poster_path)
    }

    class TopRatedMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val posterImageView: ImageView = view.findViewById(R.id.movie_poster)

        fun setImage(relativeUrl: String) {
            val posterUrl = "https://image.tmdb.org/t/p/w300$relativeUrl"
            Picasso.get()
                .load(posterUrl)
                .into(posterImageView)
        }
    }
}