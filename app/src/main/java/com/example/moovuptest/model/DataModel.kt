package com.example.moovuptest.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class DataModel(
    @Json(name = "_id")
    var _id: String,
    @Json(name = "name")
    var name: NameModel,
    @Json(name = "email")
    var email: String,
    @Json(name = "picture")
    var picture: String,
    @Json(name = "location")
    var locationModel: LocationModel,

    )