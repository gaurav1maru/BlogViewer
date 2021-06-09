package com.example.blogviewer.io.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BlogDetailModel(
    var blogModel: BlogModel? = null,
    var userModel: UserModel? = null,
    var commentModel: CommentModel? = null
) : Parcelable