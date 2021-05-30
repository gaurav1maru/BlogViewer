package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAddressModel(
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
    var geo: UserAddressGeoModel? = null
) : Parcelable