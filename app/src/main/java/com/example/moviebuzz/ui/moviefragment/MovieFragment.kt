package com.example.moviebuzz.ui.moviefragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.moviebuzz.R
import com.example.moviebuzz.factory.ViewModelFactory
import com.example.moviebuzz.network.Resource
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.movie_fragment.*
import javax.inject.Inject

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this , viewModelFactory).get(MovieViewModel::class.java)
        //register for popular movies
        viewModel.popularMovies.observe(this,
            Observer {
                Log.v("Popular Movies", it.data?.total_results.toString())
                if(it.data != null) {
                    setUpPopularMovieView(it.data)
                }
            })
        //register for now playing movies
        viewModel.nowPlayingMovies.observe(this,
            Observer{
                Log.v("Now Playing Movies", it.data?.total_results.toString())
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
