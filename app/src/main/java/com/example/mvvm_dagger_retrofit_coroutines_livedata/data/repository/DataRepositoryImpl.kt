package com.example.mvvm_dagger_retrofit_coroutines_livedata.data.repository

import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.GithubAccount
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.Resource
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.api.ApiClient
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
        private val apiClient: ApiClient
): DataRepository {

    override suspend fun fetchGithubAccount(userName: String): Deferred<Resource<GithubAccount>> {
        return withContext(Dispatchers.IO) {
            async {
                apiClient.fetchGithubAccount(userName).await()
            }
        }
    }
}