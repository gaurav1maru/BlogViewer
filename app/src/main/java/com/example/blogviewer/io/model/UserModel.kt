package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var id: Int? = null,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: UserAddressModel? = null,
    var phone: String? = null,
    var website: String? = null,
    var company: UserCompanyModel? = null
) : Parcelable