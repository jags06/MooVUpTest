package com.example.moovuptest.di

import com.example.moovuptest.BaseViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { BaseViewModel(get()) }
}