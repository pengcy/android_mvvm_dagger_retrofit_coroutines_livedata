package com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules

import dagger.Module

@Module(includes = arrayOf(ViewModelModule::class, NetworkModule::class))
class AppModule {

}