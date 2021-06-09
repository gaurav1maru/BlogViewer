package com.example.blogviewer.io.api

import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.CommentModel
import com.example.blogviewer.io.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("/posts")
    suspend fun getBlogList(): Response<List<BlogModel>>

    @GET("/users")
    suspend fun getUserList(): Response<List<UserModel>>

    @GET("/comments")
    suspend fun getCommentList(): Response<List<CommentModel>>
}