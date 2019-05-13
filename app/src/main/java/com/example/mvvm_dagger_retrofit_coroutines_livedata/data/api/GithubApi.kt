package com.example.mvvm_dagger_retrofit_coroutines_livedata.data.api


import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.GithubAccount
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}")
    fun getGithubAccount(@Path("username") username: String): Deferred<Response<GithubAccount>>
}