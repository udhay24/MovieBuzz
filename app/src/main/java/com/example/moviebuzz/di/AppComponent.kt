package com.example.moviebuzz.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
    AndroidInjectionModule::class,
    AppComponentModule::class,
    ActivityBuilderModule::class,
    ViewModelModule::class]
)
interface AppComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindsApplication(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}