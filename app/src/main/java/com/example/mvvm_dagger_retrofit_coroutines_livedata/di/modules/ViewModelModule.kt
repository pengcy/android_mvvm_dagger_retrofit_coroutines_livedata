package com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_dagger_retrofit_coroutines_livedata.ui.github.GithubActivityViewModel
import com.example.mvvm_dagger_retrofit_coroutines_livedata.di.ViewModelFactory
import com.example.mvvm_dagger_retrofit_coroutines_livedata.di.ViewModelKey
import com.example.mvvm_dagger_retrofit_coroutines_livedata.ui.github.GithubFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GithubActivityViewModel::class)
    abstract fun bindGithubActivityViewModel(mainActivityViewModel: GithubActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GithubFragmentViewModel::class)
    abstract fun bindGithubFragmentViewModel(githubFragmentViewModel: GithubFragmentViewModel) : ViewModel

}