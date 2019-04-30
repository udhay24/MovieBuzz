package com.example.moviebuzz.ui.moviefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.repository.model.movie.NowPlayingMovies
import com.example.moviebuzz.repository.model.movie.PopularMovies
import com.example.moviebuzz.repository.model.movie.TopRatedMovies
import com.example.moviebuzz.repository.model.movie.UpComingMovies
import com.squareup.picasso.Picasso

class PopularMoviesAdapter(private val context: Context?,
                           private val popularMovies: PopularMovies
)
    : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.single_image_layout, parent, false)

        return PopularMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {

        return popularMovies.results.count()
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        val url = "https://image.tmdb.org/t/p/w300${popularMovies.results[position].poster_path}"

        Picasso.get()
            .load(url)
            .into(holder.imageView)
    }

    class PopularMoviesViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        val imageView: ImageView = view.findViewById(R.id.movie_poster)
    }
}

class NowPlayingMoviesAdapter(private val context: Context?,
                              private val nowPlayingMovies: NowPlayingMovies
)
    :RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingMovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.single_image_layout, parent, false)

        return NowPlayingMovieViewHolder(view)
    }

    override fun getItemCount(): Int {

        return nowPlayingMovies.results.count()
    }

    override fun onBindViewHolder(holder: NowPlayingMovieViewHolder, position: Int) {

        val posterUrl = "https://image.tmdb.org/t/p/w300${nowPlayingMovies.results[position].poster_path}"

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

class UpComingMoviesAdapter(private val results: List<UpComingMovies.Result>) :
    RecyclerView.Adapter<UpComingMoviesAdapter.UpComingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingMoviesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_image_layout, parent, false)

        return UpComingMoviesViewHolder(view)
    }


    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: UpComingMoviesViewHolder, position: Int) {
        holder.setImage(results[position].poster_path)
    }

    class UpComingMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val posterImageView: ImageView = view.findViewById(R.id.movie_poster)

        fun setImage(relativeUrl: String) {
            val posterUrl = "https://image.tmdb.org/t/p/w200$relativeUrl"
            Picasso.get()
                .load(posterUrl)
                .into(posterImageView)
        }
    }
}