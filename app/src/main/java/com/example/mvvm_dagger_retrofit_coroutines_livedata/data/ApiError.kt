package com.example.mvvm_dagger_retrofit_coroutines_livedata.data

import androidx.annotation.StringRes
import com.example.mvvm_dagger_retrofit_coroutines_livedata.R

sealed class ApiError {
    abstract val error: Throwable?
    abstract val errorMsgResId: Int
}

data class BadRequestError(override val error : Throwable? = null, @StringRes override val errorMsgResId : Int = R.string.error_bad_request) : ApiError()
data class AuthError(override val error : Throwable? = null, @StringRes override val errorMsgResId : Int = R.string.error_not_authorized) : ApiError()
data class NotFoundError(override val error : Throwable? = null, @StringRes override val errorMsgResId : Int = R.string.error_not_found) : ApiError()
data class ServerError(override val error : Throwable? = null, @StringRes override val errorMsgResId : Int = R.string.error_server_error) : ApiError()
data class UnexpectedNullValueError(override val error : Throwable? = null, @StringRes override val errorMsgResId : Int = R.string.error_unexpected_null_error) : ApiError()
