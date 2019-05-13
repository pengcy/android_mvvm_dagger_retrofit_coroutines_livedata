package com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules

import com.example.mvvm_dagger_retrofit_coroutines_livedata.ui.github.GithubActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun contributeLoginActivity() : GithubActivity
}