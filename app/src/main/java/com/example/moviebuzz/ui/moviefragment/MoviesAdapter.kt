package com.example.moviebuzz.ui.moviefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import com.squareup.picasso.Picasso

class PopularMoviesAdapter(private val context: Context?,
                           private val popularMovie: PopularMovie)
    : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.popular_movie_view_holder, parent, false)

        return PopularMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {

        return popularMovie.results.count()
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        val url = "https://image.tmdb.org/t/p/w500${popularMovie.results[position].poster_path}"

        Picasso.get()
            .load(url)
            .into(holder.imageView)
    }

    class PopularMoviesViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        val imageView: ImageView = view.findViewById(R.id.movie_poster)
    }
}

class NowPlayingMoviesAdapter(private val context: Context?,
                              private val nowPlayingMovie: NowPlayingMovie)
    :RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingMovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.now_showing_movie_view_holder, parent, false)

        return NowPlayingMovieViewHolder(view)
    }

    override fun getItemCount(): Int {

        return nowPlayingMovie.results.count()
    }

    override fun onBindViewHolder(holder: NowPlayingMovieViewHolder, position: Int) {

        val posterUrl = "https://image.tmdb.org/t/p/w500${nowPlayingMovie.results[position].poster_path}"
        val movieName = nowPlayingMovie.results[position].title

        //set poster image
        Picasso.get()
            .load(posterUrl)
            .into(holder.moviePoster)

        //set the movie name
        holder.movieName.text = movieName

    }

    class NowPlayingMovieViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        val moviePoster: ImageView = view.findViewById(R.id.movie_poster)
        val movieName: TextView = view.findViewById(R.id.movie_name)
    }
}