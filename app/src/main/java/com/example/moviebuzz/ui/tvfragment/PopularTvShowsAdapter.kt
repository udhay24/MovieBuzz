package com.example.moviebuzz.ui.tvfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.model.tvshows.PopularTvShows
import com.example.moviebuzz.R
import com.squareup.picasso.Picasso

class PopularTvShowsAdapter(private var popularTvShows: List<PopularTvShows.Result>?) :
    RecyclerView.Adapter<PopularTvShowsAdapter.PopularTvShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvShowsViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_tv_shows_view_holder, parent, false)

        return PopularTvShowsViewHolder(view)
    }

    override fun getItemCount(): Int = popularTvShows?.size ?: 0

    override fun onBindViewHolder(holder: PopularTvShowsViewHolder, position: Int) {
        holder.setPosterImage(popularTvShows!![position].poster_path)
        holder.setTvShowTitle(popularTvShows!![position].name)
    }

    fun swapData(newData: List<PopularTvShows.Result>?) {
        popularTvShows = newData
        notifyDataSetChanged()
    }

    class PopularTvShowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val posterImageView: ImageView = view.findViewById(R.id.tv_show_poster)
        private val tvShowTitle: TextView = view.findViewById(R.id.tv_show_name)

        fun setPosterImage(posterPath: String) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w200$posterPath")
                .into(posterImageView)
        }

        fun setTvShowTitle(title: String) {
            tvShowTitle.text = title
        }
    }
}