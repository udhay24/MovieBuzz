package com.example.moviebuzz.ui.moviefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import com.example.moviebuzz.factory.ViewModelFactory
import com.example.moviebuzz.repository.model.NowPlayingMovie
import com.example.moviebuzz.repository.model.PopularMovie
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.movie_fragment.*
import timber.log.Timber
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

                Timber.v("Popular ${it.data?.results?.size ?: "nothing here"}")

                if(it.data != null) {

                    Timber.v("Popular Count ${it.data.results.size}")
                    Toast.makeText(
                        this@MovieFragment.context,
                        "Popular Count ${it.data.results.size}",
                        Toast.LENGTH_LONG
                    ).show()

                    setUpPopularMovieView(it.data)
                } else {
                    Timber.v("Popular is empty ")
                    Toast.makeText(this@MovieFragment.context, "null", Toast.LENGTH_LONG).show()
                }
            })
        //register for now playing movies
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
