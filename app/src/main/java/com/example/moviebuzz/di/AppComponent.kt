package com.example.moviebuzz.di

import android.app.Application
import com.example.moviebuzz.ui.MainActivity
import com.example.moviebuzz.ui.moviefragment.MovieFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ AndroidInjectionModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    ActivityBuilder::class ])

interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}