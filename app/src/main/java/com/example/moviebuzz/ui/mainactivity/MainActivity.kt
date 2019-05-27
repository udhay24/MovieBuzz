package com.example.moviebuzz.ui.mainactivity

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.moviebuzz.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(this, R.id.nav_controller_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieFragment, R.id.tvShowsFragment, R.id.searchFragment, R.id.aboutFragment -> {
                    bottom_navigation.apply {
                        if (visibility == View.GONE) {
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
                else -> {
                    bottom_navigation.apply {
                        if (visibility == View.VISIBLE) {
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
                }
            }
        }
    }
}
