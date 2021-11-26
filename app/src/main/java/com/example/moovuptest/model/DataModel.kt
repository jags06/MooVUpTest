package com.example.moovuptest.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    ):Parcelable