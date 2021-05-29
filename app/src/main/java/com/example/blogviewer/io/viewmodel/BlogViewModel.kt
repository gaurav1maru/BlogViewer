package com.example.blogviewer.io.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogviewer.io.api.ApiInterface
import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogViewModel : ViewModel() {
    val blogListLiveData: MutableLiveData<List<BlogModel>> = MutableLiveData<List<BlogModel>>()
    val userListLiveData: MutableLiveData<List<UserModel>> = MutableLiveData<List<UserModel>>()

    fun getBlogList() {
        val apiInterface = ApiInterface.create().getBlogList()
        apiInterface.enqueue(object : Callback<List<BlogModel>> {
            override fun onResponse(
                call: Call<List<BlogModel>>?,
                response: Response<List<BlogModel>>?
            ) {
                //TODO - delete log
                Log.d("Blog response", response!!.body()[0].title)
                if (response != null && response.isSuccessful && response.body() != null) {
                    try {
                        blogListLiveData.postValue(response.body())
                    } catch (e: Exception) {
                        blogListLiveData.postValue(arrayListOf())
                    }
                }
            }

            override fun onFailure(call: Call<List<BlogModel>>?, t: Throwable?) {
                //TODO - handle failure gracefully
            }
        })
    }

    fun getUserList() {
        val apiInterface = ApiInterface.create().getUserList()
        apiInterface.enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>?,
                response: Response<List<UserModel>>?
            ) {
                if (response != null && response.isSuccessful && response.body() != null) {
                    try {
                        userListLiveData.postValue(response.body())
                    } catch (e: Exception) {
                        userListLiveData.postValue(arrayListOf())
                    }
                }
            }

            override fun onFailure(call: Call<List<UserModel>>?, t: Throwable?) {
                //TODO - handle failure gracefully
            }
        })
    }
}