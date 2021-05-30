package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: UserAddressGeoModel
) : Parcelable