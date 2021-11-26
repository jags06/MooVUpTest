package com.example.moovuptest.di

import com.example.moovuptest.BaseViewModel
import com.example.moovuptest.ui.list.ListViewModel
import com.example.moovuptest.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
   viewModel{ MapViewModel()}
    viewModel { ListViewModel() }
    single { BaseViewModel(get()) }
}