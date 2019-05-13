package com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules

import com.example.mvvm_dagger_retrofit_coroutines_livedata.ui.github.GithubFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeGithubFragment() : GithubFragment
}