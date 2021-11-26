package com.example.moovuptest.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NameModel(
    @Json(name = "last")
    var lastName: String ,
    @Json(name = "first")
    var firstName: String
):Parcelable

