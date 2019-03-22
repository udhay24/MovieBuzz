package com.example.moviebuzz.ui.moviefragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.moviebuzz.R
import com.example.moviebuzz.factory.ViewModelFactory
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

        //register for popular movies
        viewModel.popularMovies.observe(this,
            Observer<PopularMovie> {
                setUpPopularMovieView(it)
            })

        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this , viewModelFactory).get(MovieViewModel::class.java)
    }

    private fun setUpPopularMovieView(popularMovie: PopularMovie){

        popular_movies_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        popular_movies_recycler_view.adapter = PopularMoviesAdapter(context, popularMovie)
    }
}
