package com.example.moovuptest.ui.list

import androidx.test.core.app.ApplicationProvider
import com.example.moovuptest.di.apiModule
import com.example.moovuptest.di.networkModule
import com.example.moovuptest.di.viewModelModule
import org.junit.After
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModulesTest : KoinTest {

    @Test
    fun verifyKoinApp() {
        koinApplication {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(networkModule, apiModule, viewModelModule)
            checkModules()
        }

    }

    @After
    fun after() {
        stopKoin()
    }
}