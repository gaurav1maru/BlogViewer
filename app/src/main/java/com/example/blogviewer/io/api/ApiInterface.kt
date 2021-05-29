package com.example.blogviewer.io.api

import com.example.blogviewer.BlogModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getBlogList(): Call<List<BlogModel>>

//    @GET("users")
//    fun getUserList(): Call<List<UserModel>>

    companion object {

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private const val BLOG_POSTS_URL = "posts"
        private const val USERS_URL = "users"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}