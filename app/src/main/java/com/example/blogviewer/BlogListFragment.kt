package com.example.blogviewer

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blogviewer.adapter.BlogAdapter
import com.example.blogviewer.databinding.FragmentBlogListBinding
import com.example.blogviewer.io.model.BlogDetailModel
import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.CommentModel
import com.example.blogviewer.io.model.UserModel
import com.example.blogviewer.io.viewmodel.BlogViewModel


class BlogListFragment : Fragment(),
    BlogAdapter.OnItemClickListener {
    private lateinit var blogViewModel: BlogViewModel
    private var binding: FragmentBlogListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blog_list, container, false)
        binding = FragmentBlogListBinding.bind(rootView)
        blogViewModel = ViewModelProvider.NewInstanceFactory().create(BlogViewModel::class.java)
        activity?.let {
            setUpViews(it)
            getBlogs()
        }

        return rootView
    }

    private fun setUpViews(activity: Activity) {
        val mLayoutManager = LinearLayoutManager(activity)
        binding?.recyclerView?.layoutManager = mLayoutManager
    }

    private fun getBlogs() {
        blogViewModel.fetchBlogList()
        blogViewModel.blogListLiveData.observe(this, {
            getUsers()
        })
    }

    private fun getUsers() {
        blogViewModel.fetchUserList()
        blogViewModel.userListLiveData.observe(this, {
            //TODO - can do lay loading on list item click if comments are not needed here
            //use API endpoint "/posts/{postId}/comments" instead of "comments"
            getComments()
        })
    }

    private fun getComments() {
        blogViewModel.fetchCommentList()
        blogViewModel.commentListLiveData.observe(this, {
            val adapter = BlogAdapter(
                prepareData(
                    blogViewModel.blogListLiveData.value,
                    blogViewModel.userListLiveData.value,
                    blogViewModel.commentListLiveData.value
                ),
                this
            )
            binding?.recyclerView?.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    private fun prepareData(
        blogList: List<BlogModel>?,
        userList: List<UserModel>?,
        commentList: List<CommentModel>?
    ): List<BlogDetailModel> {
        val list = ArrayList<BlogDetailModel>()
        blogList?.forEach { blog ->
            list.add(BlogDetailModel(
                blog,
                userList?.find { it.id == blog.userId },
                //TODO - this gets just the first comment, it needs to be the list
                commentList?.find { it.postId == blog.id }
            ))
        }

        return list
    }

    override fun onItemClick(blogDetailModel: BlogDetailModel) {
        val act: FragmentActivity = activity ?: return

        Toast.makeText(
            activity, "You selected " + blogDetailModel.blogModel?.title + "!",
            Toast.LENGTH_SHORT
        ).show()
        val detailsFragment =
            BlogDetailsFragment.newInstance(blogDetailModel)
        act.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, detailsFragment, "blogDetails")
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}