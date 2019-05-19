package com.example.moviebuzz.ui.moviedetail

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.moviebuzz.R
import com.example.moviebuzz.databinding.MovieDetailFragmentBinding
import com.example.moviebuzz.network.BaseUrl
import com.example.moviebuzz.network.NetworkStatus
import com.example.moviebuzz.repository.model.movie.Movie
import com.example.moviebuzz.ui.mainactivity.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: MovieDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.movie.observe(this, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> {
                    setUpMovieLayout(it.data!!)
                }
                NetworkStatus.LOADING -> Toast.makeText(
                    context,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
                NetworkStatus.FAILURE -> Toast.makeText(
                    context,
                    "Failure",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        description.setOnClickListener {
            TransitionManager.beginDelayedTransition(view as ViewGroup)
            description.maxLines = Integer.MAX_VALUE
            description.ellipsize = TextUtils.TruncateAt.MARQUEE
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).bottom_navigation.apply {
            animate()
                .translationYBy(this.height.toFloat())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {}
                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {
                        this@apply.visibility = View.GONE
                    }
                })
                .duration = 200
        }
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).bottom_navigation.apply {
            animate()
                .translationYBy(-this.height.toFloat())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {}
                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {
                        this@apply.visibility = View.VISIBLE
                    }
                })
                .duration = 200
        }
    }

    private fun setUpMovieLayout(movie: Movie) {
        Picasso.get()
            .load(BaseUrl.getBackdropPath(movie.backdropPath))
            .into(background_image)

//        Picasso.get()
//            .load(BaseUrl.getPosterPath(movie.posterPath))
//            .into(movie_poster_image)

//        movie_title.text = movie.title
        Toast.makeText(context, "Id: ${movie.id}", Toast.LENGTH_LONG).show()
    }
}
