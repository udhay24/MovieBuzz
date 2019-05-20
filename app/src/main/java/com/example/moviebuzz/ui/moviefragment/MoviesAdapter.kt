package com.example.moviebuzz.ui.moviefragment

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.network.BaseUrl
import com.example.moviebuzz.repository.model.movie.NowPlayingMovies
import com.example.moviebuzz.repository.model.movie.PopularMovies
import com.example.moviebuzz.repository.model.movie.TopRatedMovies
import com.example.moviebuzz.repository.model.movie.UpComingMovies
import com.squareup.picasso.Picasso

class PopularMoviesAdapter(
    private val popularMovies: PopularMovies,
    private val movieClickListener: (Int) -> Unit
)
    : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.single_image_layout, parent, false)

        val holder = PopularMoviesViewHolder(view, movieClickListener)
        view.setOnClickListener(holder)
        return holder
    }

    override fun getItemCount(): Int = popularMovies.results.count()

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.setPosterImage(popularMovies.results[position].poster_path)
    }

    inner class PopularMoviesViewHolder(
        private val view: View,
        private val movieClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val moviePoster: ImageView = view.findViewById(R.id.movie_poster)

        fun setPosterImage(relativeUrl: String) {
            val posterUrl = BaseUrl.getPosterPath(relativeUrl)

            Picasso.get()
                .load(posterUrl)
                .into(moviePoster)
        }

        override fun onClick(v: View?) {
            movieClickListener.invoke(popularMovies.results[adapterPosition].id)
        }
    }
}

class NowPlayingMoviesAdapter(
    private val nowPlayingMovies: NowPlayingMovies,
    private val movieClickListener: (Int) -> Unit
)
    :RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingMovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_image_layout, parent, false)

        val holder = NowPlayingMovieViewHolder(view, movieClickListener)
        view.setOnClickListener(holder)
        return holder
    }

    override fun getItemCount(): Int = nowPlayingMovies.results.count()

    override fun onBindViewHolder(holder: NowPlayingMovieViewHolder, position: Int) {
        holder.setPosterImage(nowPlayingMovies.results[position].poster_path)
    }

    inner class NowPlayingMovieViewHolder(private val view: View, private val movieClickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private val moviePoster: ImageView = view.findViewById(R.id.movie_poster)

        fun setPosterImage(relativeUrl: String) {
            val posterUrl = BaseUrl.getPosterPath(relativeUrl)

            Picasso.get()
                .load(posterUrl)
                .into(moviePoster)
        }

        override fun onClick(v: View?) {
            movieClickListener.invoke(nowPlayingMovies.results[adapterPosition].id)
        }
    }
}

class TopRatedMoviesAdapter(
    private val results: List<TopRatedMovies.Result>,
    private val movieClickListener: (Int) -> Unit
) : RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_image_layout, parent, false)

        val holder = TopRatedMoviesViewHolder(view, movieClickListener)
        view.setOnClickListener(holder)
        return holder
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        results[position].poster_path.apply {
            if (this != null) {
                holder.setImage(this)
            }
        }
    }

    inner class TopRatedMoviesViewHolder(view: View, private val movieClickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private val posterImageView: ImageView = view.findViewById(R.id.movie_poster)

        fun setImage(relativeUrl: String) {
            val posterUrl = BaseUrl.getPosterPath(relativeUrl)
            Picasso.get()
                .load(posterUrl)
                .into(posterImageView)
        }

        override fun onClick(v: View?) {
            movieClickListener.invoke(results[adapterPosition].id)
        }
    }
}

class UpComingMoviesAdapter(
    private val results: List<UpComingMovies.Result>,
    private val movieClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<UpComingMoviesAdapter.UpComingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingMoviesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_image_layout, parent, false)

        val holder = UpComingMoviesViewHolder(view, movieClickListener)
        view.setOnClickListener(holder)
        return holder
    }


    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: UpComingMoviesViewHolder, position: Int) {
        holder.setImage(results[position].poster_path)
    }

    inner class UpComingMoviesViewHolder(view: View, private val clickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private val posterImageView: ImageView = view.findViewById(R.id.movie_poster)

        fun setImage(relativeUrl: String) {
            val posterUrl = BaseUrl.getPosterPath(relativeUrl)
            Picasso.get()
                .load(posterUrl)
                .into(posterImageView)
        }

        override fun onClick(v: View?) {
            clickListener.invoke(results[adapterPosition].id)
        }
    }
}

class MarginItemDecorator(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                left = margin
            }
            top = margin
            right = margin
            bottom = margin
        }
    }
}

