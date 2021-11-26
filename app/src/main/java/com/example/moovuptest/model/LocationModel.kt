package com.example.moovuptest.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationModel(
    @Json(name = "latitude")
    var latitude: Double,
    @Json(name = "longitude")
    var longitude: Double?=null
):Parcelable
