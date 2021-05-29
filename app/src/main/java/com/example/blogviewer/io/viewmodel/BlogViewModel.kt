package com.example.blogviewer.io.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogviewer.BlogModel
import com.example.blogviewer.io.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogViewModel : ViewModel() {
    val blogListLiveData: MutableLiveData<List<BlogModel>> = MutableLiveData<List<BlogModel>>()
    val blogLiveData: MutableLiveData<BlogModel> = MutableLiveData<BlogModel>()

//    fun getBlogList(){
//        val apiInterface = ApiInterface.create().getBlogList()
//        apiInterface.enqueue( object : Callback<List<BlogModel>>{
//            override fun onResponse(call: Call<List<BlogModel>>?, response: Response<List<BlogModel>>?) {
//                response?.body()?.let { try {
//                    blogListLiveData.postValue(it)
//                } catch (e: Exception) {
//                    blogListLiveData.postValue(arrayListOf())
//                } }
//            }
//
//            override fun onFailure(call: Call<List<BlogModel>>?, t: Throwable?) {
//                //TODO - handle failure gracefully
//            }
//        })
//    }

    fun getBlogList() {
        val apiInterface = ApiInterface.create().getBlogList()
        apiInterface.enqueue(object : Callback<List<BlogModel>> {
            override fun onResponse(
                call: Call<List<BlogModel>>?,
                response: Response<List<BlogModel>>?
            ) {
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

    fun getBlog(id: Int) {

    }
}