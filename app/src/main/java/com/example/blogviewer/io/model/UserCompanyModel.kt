package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCompanyModel(
    val name: String,
    val catchPhrase: String,
    val bs: String
) : Parcelable