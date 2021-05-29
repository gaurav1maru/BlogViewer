package com.example.blogviewer

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogviewer.adapter.BlogAdapter
import com.example.blogviewer.io.viewmodel.BlogViewModel


class BlogListFragment : Fragment(),
    BlogAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blog_list, container, false)
        setUpViews(rootView)

        activity?.let { getBlogs(it) }

        return rootView
    }

    private fun setUpViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        val mLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = mLayoutManager
    }

    private fun getBlogs(activity: Activity) {
        val blogViewModel: BlogViewModel =
            ViewModelProvider.NewInstanceFactory().create(BlogViewModel::class.java)
        blogViewModel.getBlogList()
        blogViewModel.blogListLiveData.observe(this, Observer {
            val adapter = BlogAdapter(
                activity,
                it, this
            )
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(blogModel: BlogModel) {
        //TODO - switch to detail fragment
        //intent.putExtra("blog", blogModel)
    }
}