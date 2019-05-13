package com.example.mvvm_dagger_retrofit_coroutines_livedata.data.api

import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.*
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.GithubAccount
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.Resource
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiClient constructor(val githubApi: GithubApi) {

    suspend fun fetchGithubAccount(userName: String): Deferred<Resource<GithubAccount>> {
        return withContext(Dispatchers.IO) {
            async {
                val response = githubApi.getGithubAccount(userName).await()
                response.mapToResource()
            }
        }
    }

    private fun <T> Response<T>.mapToResource(): Resource<T> {
        val resultCode = code()
        val resource = Resource<T>()
        resource.apiError = when {
            resultCode == Constants.ERROR_CODE_BAD_REQUEST -> {
                BadRequestError()
            }
            resultCode == Constants.ERROR_CODE_UNAUTHORIZED -> {
                AuthError()
            }
            resultCode == Constants.ERROR_CODE_NOT_FOUND -> {
                NotFoundError()
            }
            resultCode == Constants.ERROR_CODE_SERVER_ERROR -> {
                ServerError()
            }
            else -> {
                null
            }
        }

        resource.data = body()

        if (resource.apiError == null && resource.data == null) {
            resource.apiError = UnexpectedNullValueError()
        }

        return resource
    }

}