package com.example.moviebuzz.ui.moviefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.data.network.NetworkStatus
import com.example.domain.entities.model.movie.LatestMovies
import com.example.domain.entities.model.movie.NowPlayingMovies
import com.example.domain.entities.model.movie.PopularMovies
import com.example.domain.entities.model.movie.TopRatedMovies
import com.example.domain.entities.model.movie.UpComingMovies
import com.example.moviebuzz.R
import com.squareup.picasso.Picasso
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
        viewModel.popularMovies.observe(this, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> setUpPopularMovieView(it.data!!)
                NetworkStatus.LOADING -> Toast.makeText(
                    this@MovieFragment.context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
                NetworkStatus.FAILURE -> Toast.makeText(
                    this@MovieFragment.context,
                    "Error Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.nowPlayingMovies.observe(this, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> setUpNowPlayingMovies(it.data!!)
                NetworkStatus.LOADING -> Toast.makeText(
                    this@MovieFragment.context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
                NetworkStatus.FAILURE -> Toast.makeText(
                    this@MovieFragment.context,
                    "Error Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.latestMovie.observe(this, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> showLatestMovie(it.data!!)
                NetworkStatus.LOADING -> Toast.makeText(
                    this@MovieFragment.context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
                NetworkStatus.FAILURE -> Toast.makeText(
                    this@MovieFragment.context,
                    "Error Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.topRatedMovies.observe(this, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> showTopRatedMovies(it.data!!)
                NetworkStatus.LOADING -> Toast.makeText(
                    this@MovieFragment.context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
                NetworkStatus.FAILURE -> {
                    Toast.makeText(
                        this@MovieFragment.context,
                        "Error Try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    Timber.e(it.message)
                }
            }
        })

        viewModel.upcomingMovies.observe(this, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> showUpComingMovies(it.data!!)
                NetworkStatus.LOADING -> Toast.makeText(
                    this@MovieFragment.context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
                NetworkStatus.FAILURE -> Toast.makeText(
                    this@MovieFragment.context,
                    "Error Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    private fun setUpPopularMovieView(popularMovies: PopularMovies) {
        popular_movies_recycler_view.adapter = PopularMoviesAdapter(popularMovies) { openMovieDetailView(it) }
        popular_movies_recycler_view.addItemDecoration(
            MarginItemDecorator(
                resources.getDimensionPixelSize(R.dimen.recycler_view_margin)
            )
        )
    }

    private fun setUpNowPlayingMovies(nowPlayingMovies: NowPlayingMovies) {
        now_playing_recycler_view.adapter = NowPlayingMoviesAdapter(nowPlayingMovies) { openMovieDetailView(it) }
        now_playing_recycler_view.addItemDecoration(
            MarginItemDecorator(
                resources.getDimensionPixelSize(R.dimen.recycler_view_margin)
            )
        )
    }

    private fun showLatestMovie(latestMovies: LatestMovies) {
        latest_movie_title.text = latestMovies.title
        val imageUrl = "https://image.tmdb.org/t/p/w300${latestMovies.poster_path}"
        Picasso.get()
            .load(imageUrl)
            .into(latest_movie_poster)
        latest_movie_container.setOnClickListener {
            openMovieDetailView(latestMovies.id)
        }
    }

    private fun showTopRatedMovies(results: List<TopRatedMovies.Result>) {
        top_rated_movie_recycler_view.adapter = TopRatedMoviesAdapter(results) { openMovieDetailView(it) }
        top_rated_movie_recycler_view.addItemDecoration(
            MarginItemDecorator(
                resources.getDimensionPixelSize(R.dimen.recycler_view_margin)
            )
        )
    }

    private fun showUpComingMovies(results: List<UpComingMovies.Result>) {
        up_coming_movie_recycler_view.adapter = UpComingMoviesAdapter(results) { openMovieDetailView(it) }
        up_coming_movie_recycler_view.addItemDecoration(
            MarginItemDecorator(
                resources.getDimensionPixelSize(R.dimen.recycler_view_margin)
            )
        )
    }

    private fun openMovieDetailView(movieId: Int) {
        val navDirections = MovieFragmentDirections.actionMovieFragmentToMovieDetail(movieId)
        findNavController().navigate(navDirections)
    }
}
