package com.example.moovuptest.model

import com.squareup.moshi.Json

data class LocationModel(
    @Json(name = "latitude")
    var latitude: Double,
    @Json(name = "longitude")
    var longitude: Double
)
