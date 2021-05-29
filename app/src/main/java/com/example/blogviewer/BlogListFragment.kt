package com.example.blogviewer

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogviewer.adapter.BlogAdapter
import com.example.blogviewer.io.model.BlogDetailModel
import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.UserModel
import com.example.blogviewer.io.viewmodel.BlogViewModel


class BlogListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var blogViewModel: BlogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blog_list, container, false)
        setUpViews(rootView)

        blogViewModel = ViewModelProvider.NewInstanceFactory().create(BlogViewModel::class.java)
        activity?.let { getBlogs(it) }

        return rootView
    }

    private fun setUpViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val mLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = mLayoutManager
    }

    private fun getBlogs(activity: Activity) {
        blogViewModel.getBlogList()
        blogViewModel.blogListLiveData.observe(this, {
            getUsers(activity)
        })
    }

    private fun getUsers(activity: Activity) {
        blogViewModel.getUserList()
        blogViewModel.userListLiveData.observe(this, {
            val adapter = BlogAdapter(
                activity,
                prepareData(
                    blogViewModel.blogListLiveData.value,
                    blogViewModel.userListLiveData.value
                ),
                activity as BlogAdapter.OnItemClickListener
            )
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    private fun prepareData(
        blogList: List<BlogModel>?,
        userList: List<UserModel>?
    ): List<BlogDetailModel> {
        var list = ArrayList<BlogDetailModel>()
        if (blogList != null) {
            for (blog: BlogModel in blogList) {
                list.add(BlogDetailModel(blog, userList?.find { it.id == blog.userId }))
            }
        }

        return list
    }
}