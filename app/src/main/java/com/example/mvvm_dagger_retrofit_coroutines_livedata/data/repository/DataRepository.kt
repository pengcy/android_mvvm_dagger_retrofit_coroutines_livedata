package com.example.mvvm_dagger_retrofit_coroutines_livedata.data.repository

import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.GithubAccount
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.Resource
import kotlinx.coroutines.Deferred

interface DataRepository {
    suspend fun fetchGithubAccount(userName: String): Deferred<Resource<GithubAccount>>
}