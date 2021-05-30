package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCompanyModel(
    var name: String? = null,
    var catchPhrase: String? = null,
    var bs: String? = null
) : Parcelable