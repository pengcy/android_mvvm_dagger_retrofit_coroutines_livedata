package com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules

import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.repository.DataRepository
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDataRepostory(dataRepository: DataRepositoryImpl): DataRepository
}