package com.example.moviebuzz.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.moviebuzz.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(this, R.id.nav_controller_fragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        setupWithNavController(bottomNavigationView, navController)
        if (bottomNavigationView == null) {
            Timber.v("Bottom view is null")
        } else {
            Timber.v("Bottom is Not null")
        }
        FragmentManager.OnBackStackChangedListener {
            Timber.v("Backstack")
        }
    }
}
