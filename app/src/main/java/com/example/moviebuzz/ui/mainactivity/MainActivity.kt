package com.example.moviebuzz.ui.mainactivity

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moviebuzz.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = findNavController(this, R.id.nav_controller_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setupWithNavController(bottomNavigationView, navController)
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movie_fragment, R.id.tv_shows_fragment, R.id.search_fragment, R.id.about_fragment ->
                    bottom_navigation.apply {
                        if (visibility == View.GONE)
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
                else ->
                    bottom_navigation.apply {
                        if (visibility == View.VISIBLE)
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

    override fun onSupportNavigateUp() = navController.navigateUp()
}
