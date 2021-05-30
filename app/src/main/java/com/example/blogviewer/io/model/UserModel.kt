package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: UserAddressModel,
    val phone: String,
    val website: String,
    val company: UserCompanyModel
) : Parcelable