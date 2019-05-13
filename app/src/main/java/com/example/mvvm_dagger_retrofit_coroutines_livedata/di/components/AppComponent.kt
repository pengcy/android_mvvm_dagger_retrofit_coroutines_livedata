package com.example.mvvm_dagger_retrofit_coroutines_livedata.di.components

import android.content.Context
import com.example.mvvm_dagger_retrofit_coroutines_livedata.MyApp
import com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules.AppModule
import com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules.ActivityModule
import com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules.FragmentModule
import com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        ActivityModule::class,
        FragmentModule::class])
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicatonContext: Context): AppComponent
    }
}