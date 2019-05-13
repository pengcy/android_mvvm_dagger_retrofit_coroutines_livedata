package com.example.mvvm_dagger_retrofit_coroutines_livedata.di.modules

import android.content.Context
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.api.ApiClient
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.api.GithubApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val httpCacheDirectory = File(context.getCacheDir(), "http-cache")
        return Cache(httpCacheDirectory, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            var cacheControl = CacheControl.Builder()
                    .maxAge(1, TimeUnit.MINUTES)
                    .build()

            response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(cache : Cache, networkCacheInterceptor : Interceptor) : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()

    }

    @Provides
    @Singleton
    internal fun provideRetrofit(httpClient : OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpClient)
                .build()

    }

    @Provides
    @Singleton
    internal fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideGithubApiClient(githubApi: GithubApi): ApiClient {
        return ApiClient(githubApi)
    }


}