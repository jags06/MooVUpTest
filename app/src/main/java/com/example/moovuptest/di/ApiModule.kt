package com.example.moovuptest.di

import com.example.moovuptest.network.NetworkApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(NetworkApi::class.java) }
}