package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BlogModel(
    var userId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var body: String? = null
) : Parcelable