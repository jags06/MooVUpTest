package com.example.moovuptest.network

import android.content.Context
import com.example.moovuptest.R
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer " + context.getString(R.string.token))
            .build()
        return chain.proceed(request)

    }

}