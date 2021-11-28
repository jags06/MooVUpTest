package com.example.moovuptest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moovuptest.model.DataModel
import com.example.moovuptest.network.NetworkApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseViewModel(private val networkApi: NetworkApi) : ViewModel() {
    var loading = MutableLiveData<Boolean>()
    val users = MutableLiveData<List<DataModel>>()
    val usersLoadError = MutableLiveData<String?>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun callData() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = networkApi.getData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    users.value = response.body()
                    usersLoadError.value = null
                    loading.value = false

                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
        usersLoadError.value = ""
        loading.value = false
    }

    private fun onError(message: String) {
        usersLoadError.value = message
        loading.value = false
    }
}