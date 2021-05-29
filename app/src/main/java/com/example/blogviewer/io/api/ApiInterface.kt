package com.example.blogviewer.io.api

import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.UserModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getBlogList(): Call<List<BlogModel>>

    @GET("users")
    fun getUserList(): Call<List<UserModel>>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}