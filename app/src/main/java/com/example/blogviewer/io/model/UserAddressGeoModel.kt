package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAddressGeoModel(
    val lat: String,
    val lng: String
) : Parcelable