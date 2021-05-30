package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BlogDetailModel(
    val blogModel: BlogModel,
    val userModel: UserModel?
) : Parcelable