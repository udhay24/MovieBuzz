package com.example.moviebuzz.ui.moviefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.network.NetworkStatus
import com.example.moviebuzz.repository.model.movie.NowPlayingMovie
import com.example.moviebuzz.repository.model.movie.PopularMovie
import kotlinx.android.synthetic.main.movie_fragment.*
import org.koin.android.ext.android.inject

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private val viewModel: MovieViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.popularMovies.observe(this,
            Observer {
                when (it.status) {
                    NetworkStatus.SUCCESS -> {
                        setUpPopularMovieView(it.data!!)
                    }
                    NetworkStatus.LOADING -> {
                        Toast.makeText(this@MovieFragment.context, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    NetworkStatus.FAILURE -> {
                        Toast.makeText(this@MovieFragment.context, "Error Try again", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        viewModel.nowPlayingMovies.observe(this,
            Observer{
                when (it.status) {
                    NetworkStatus.SUCCESS -> {
                        setUpNowPlayingMovies(it.data!!)
                    }
                    NetworkStatus.LOADING -> {
                        Toast.makeText(this@MovieFragment.context, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    NetworkStatus.FAILURE -> {
                        Toast.makeText(this@MovieFragment.context, "Error Try again", Toast.LENGTH_SHORT).show()
                    }
                }
        })

        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    private fun setUpPopularMovieView(popularMovie: PopularMovie){
        popular_movies_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        popular_movies_recycler_view.adapter = PopularMoviesAdapter(context, popularMovie)
    }

    private fun setUpNowPlayingMovies(nowPlayingMovies: NowPlayingMovie){
        now_playing_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        now_playing_recycler_view.adapter = NowPlayingMoviesAdapter(context, nowPlayingMovies)
    }
}
