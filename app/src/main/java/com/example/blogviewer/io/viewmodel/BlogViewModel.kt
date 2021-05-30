package com.example.blogviewer.io.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogviewer.io.api.ApiAdapter
import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class BlogViewModel : ViewModel(), CoroutineScope by MainScope() {
    val blogListLiveData: MutableLiveData<List<BlogModel>> = MutableLiveData<List<BlogModel>>()
    val userListLiveData: MutableLiveData<List<UserModel>> = MutableLiveData<List<UserModel>>()

    fun fetchBlogList() {
        launch(Dispatchers.Main) {
            try {
                val response = ApiAdapter.apiClient.getBlogList()
                if (response.isSuccessful && response.body() != null) {
                    blogListLiveData.postValue(response.body())
                } else {
                    blogListLiveData.postValue(arrayListOf())
                }
            } catch (e: Exception) {
                //TODO - handle failure gracefully
                Log.e("fetchBlogList", "fail")
                e.printStackTrace()
            }
        }
    }

    fun getBlogList(): LiveData<List<BlogModel>> {
        return blogListLiveData
    }

    fun fetchUserList() {
        launch(Dispatchers.Main) {
            try {
                val response = ApiAdapter.apiClient.getUserList()
                if (response.isSuccessful && response.body() != null) {
                    userListLiveData.postValue(response.body())
                } else {
                    userListLiveData.postValue(arrayListOf())
                }
            } catch (e: Exception) {
                //TODO - handle failure gracefully
                Log.e("fetchUserList", "fail")
                e.printStackTrace()
            }
        }
    }

    fun getUserList(): LiveData<List<UserModel>> {
        return userListLiveData
    }
}