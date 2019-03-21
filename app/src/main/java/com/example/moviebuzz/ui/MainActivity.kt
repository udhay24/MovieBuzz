package com.example.moviebuzz.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.moviebuzz.R
import com.example.moviebuzz.di.DaggerAppComponent
import com.example.moviebuzz.ui.moviefragment.MovieViewModel
import dagger.android.*
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : DaggerActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
