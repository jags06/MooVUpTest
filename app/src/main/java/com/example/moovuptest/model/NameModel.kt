package com.example.moovuptest.model

import com.squareup.moshi.Json

data class NameModel(
    @Json(name = "last")
    var lastName: String ,
    @Json(name = "first")
    var firstName: String
)

