package com.example.moovuptest.network

import com.example.moovuptest.model.DataModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET(".")
    suspend fun getData():Response<List<DataModel>>

}