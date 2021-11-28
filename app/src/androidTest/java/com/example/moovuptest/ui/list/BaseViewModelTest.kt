package com.example.moovuptest.ui.list

import com.example.moovuptest.BaseViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BaseViewModelTest : KoinTest {
    private lateinit var baseViewModel: BaseViewModel

    @Test
    fun checkBaseViewModelIsNotNull() {
        baseViewModel = BaseViewModel(get())
        assertNotNull(baseViewModel)
    }

    @Test
    fun isLiveDataEmitting_observeForever() {
        baseViewModel = BaseViewModel(get())
        baseViewModel.loading.postValue(false)
        assertEquals(false, baseViewModel.loading.value == false)
    }

    @Test
    fun testCallData() = runBlocking {
        baseViewModel = BaseViewModel(get())
        baseViewModel.loading.postValue(true)
        val data = baseViewModel.callData()
        assertNotNull(data)
    }
}