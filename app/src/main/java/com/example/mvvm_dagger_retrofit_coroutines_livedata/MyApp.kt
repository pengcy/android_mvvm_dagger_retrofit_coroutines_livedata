package com.example.mvvm_dagger_retrofit_coroutines_livedata

import com.example.mvvm_dagger_retrofit_coroutines_livedata.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication() {

    companion object {
        var instance: MyApp? = null
            private set
    }

    override fun applicationInjector() : AndroidInjector<MyApp> =
            DaggerAppComponent
                    .factory()
                    .create(applicationContext)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}