package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentModel(
    var postId: Int? = null,
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
) : Parcelable