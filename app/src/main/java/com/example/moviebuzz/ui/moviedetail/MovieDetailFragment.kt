package com.example.moviebuzz.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviebuzz.R
import com.example.moviebuzz.databinding.MovieDetailFragmentBinding
import com.example.moviebuzz.network.NetworkStatus
import com.example.moviebuzz.ui.mainactivity.MainActivity
import com.example.moviebuzz.ui.moviefragment.MarginItemDecorator
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModel { parametersOf(args.movieId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: MovieDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.similarMovies.observe(this, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> {
                    similar_movies_recycler_view.adapter = SimilarMoviesAdapter(
                        it.data!!
                    ) { movieId: Int ->
                        val navDirections = MovieDetailFragmentDirections.actionMovieDetailSelf(movieId)
                        findNavController().navigate(navDirections)
                    }
                    similar_movies_recycler_view.addItemDecoration(MarginItemDecorator(10))
                }
                else -> Unit
            }
        }
        )

        viewModel.movieName.observe(this, Observer {
            (activity as MainActivity).supportActionBar?.title = it
        })
    }
}
