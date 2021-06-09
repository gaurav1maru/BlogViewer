package com.example.blogviewer.io.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogviewer.io.api.ApiAdapter
import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.CommentModel
import com.example.blogviewer.io.model.UserModel
import kotlinx.coroutines.*

class BlogViewModel : ViewModel(), CoroutineScope by MainScope() {
    val blogListLiveData: MutableLiveData<List<BlogModel>> = MutableLiveData<List<BlogModel>>()
    val userListLiveData: MutableLiveData<List<UserModel>> = MutableLiveData<List<UserModel>>()
    val commentListLiveData: MutableLiveData<List<CommentModel>> =
        MutableLiveData<List<CommentModel>>()

    fun fetchBlogList() {
        launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiAdapter.apiClient.getBlogList()
                }
                if (response.isSuccessful && response.body() != null) {
                    blogListLiveData.postValue(response.body())
                } else {
                    blogListLiveData.postValue(arrayListOf())
                }
            } catch (e: Exception) {
                Log.e("fetchBlogList", "fail - " + e.localizedMessage)
                blogListLiveData.postValue(arrayListOf())
            }
        }
    }

    fun fetchUserList() {
        launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiAdapter.apiClient.getUserList()
                }
                if (response.isSuccessful && response.body() != null) {
                    userListLiveData.postValue(response.body())
                } else {
                    userListLiveData.postValue(arrayListOf())
                }
            } catch (e: Exception) {
                Log.e("fetchUserList", "fail - " + e.localizedMessage)
                userListLiveData.postValue(arrayListOf())
            }
        }
    }

    fun fetchCommentList() {
        launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiAdapter.apiClient.getCommentList()
                }
                if (response.isSuccessful && response.body() != null) {
                    commentListLiveData.postValue(response.body())
                } else {
                    commentListLiveData.postValue(arrayListOf())
                }
            } catch (e: Exception) {
                Log.e("fetchCommentList", "fail - " + e.localizedMessage)
                commentListLiveData.postValue(arrayListOf())
            }
        }
    }
}