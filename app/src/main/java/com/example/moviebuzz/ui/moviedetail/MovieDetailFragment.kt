package com.example.moviebuzz.ui.moviedetail

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.moviebuzz.R
import com.example.moviebuzz.ui.mainactivity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by inject { parametersOf(args.movieId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.movie.observe(this, Observer {
            Toast.makeText(this@MovieDetailFragment.context, it.data?.originalTitle, Toast.LENGTH_SHORT).show()
        })
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
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
}
