package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAddressGeoModel(
    var lat: String? = null,
    var lng: String? = null
) : Parcelable