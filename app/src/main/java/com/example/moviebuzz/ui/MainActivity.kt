package com.example.moviebuzz.ui

import android.os.Bundle
import com.example.moviebuzz.R
import dagger.android.DaggerActivity

class MainActivity : DaggerActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
