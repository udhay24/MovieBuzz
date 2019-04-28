package com.example.moviebuzz.ui.moviefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import kotlinx.android.synthetic.main.movie_fragment.*
import org.koin.android.ext.android.inject
import timber.log.Timber

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
                if(it.data != null) {
                    setUpPopularMovieView(it.data)
                } else {
                    Timber.v("Popular movie list is empty ")
                }
            })

        viewModel.nowPlayingMovies.observe(this,
            Observer{

                if(it.data != null) {
                    setUpNowPlayingMovies(it.data)
                }
        })

        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    private fun setUpPopularMovieView(popularMovie: PopularMovie){
        popular_movies_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        popular_movies_recycler_view.adapter = PopularMoviesAdapter(context, popularMovie)
    }

    private fun setUpNowPlayingMovies(nowPlayingMovies: NowPlayingMovie){
        now_playing_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        now_playing_recycler_view.adapter = NowPlayingMoviesAdapter(context, nowPlayingMovies)
    }
}
