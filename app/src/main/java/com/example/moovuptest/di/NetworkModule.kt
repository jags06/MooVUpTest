package com.example.moovuptest.di

import com.example.moovuptest.BuildConfig
import com.example.moovuptest.R
import com.example.moovuptest.network.HeaderInterceptor
import com.example.moovuptest.utililty.hasNetwork
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L

val networkModule = module {
    single { Cache(androidApplication().cacheDir, 100L * 1024 * 1024) }

    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }.addInterceptor(HeaderInterceptor(androidApplication())).addInterceptor(
            HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.HEADERS
                }
            }).addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(androidContext()))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(androidApplication().getString(R.string.url))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get())
            .build()

    }


}
