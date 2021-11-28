package com.example.moovuptest.ui.list

import com.example.moovuptest.BaseViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BaseViewModelTest : KoinTest {
    private lateinit var baseViewModel: BaseViewModel

    @Before
    fun setup() {
        baseViewModel = BaseViewModel(get())
    }

    @Test
    fun checkBaseViewModelIsNotNull() {
        assertNotNull(baseViewModel)
    }

    @Test
    fun isLiveDataEmitting_observeForever() {
        baseViewModel.loading.postValue(false)
        assertEquals(false, baseViewModel.loading.value == false)
    }

    @Test
    fun testCallData() = runBlocking {
        baseViewModel.loading.postValue(true)
        val data = baseViewModel.callData()
        assertNotNull(data)
    }

    
}