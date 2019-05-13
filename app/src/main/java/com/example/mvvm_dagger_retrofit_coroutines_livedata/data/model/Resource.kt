package com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model

import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.ApiError

data class Resource<T>(
        var data: T? = null,
        var apiError: ApiError? = null
)