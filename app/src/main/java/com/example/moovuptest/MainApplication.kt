package com.example.moovuptest

import android.app.Application
import com.example.moovuptest.di.apiModule
import com.example.moovuptest.di.networkModule
import com.example.moovuptest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        val module = listOf(
            networkModule,
            apiModule,
            viewModelModule
        )
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)
            modules(module)
        }
    }
}