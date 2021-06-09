package com.example.blogviewer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blogviewer.databinding.FragmentBlogDetailsBinding
import com.example.blogviewer.io.model.BlogDetailModel
import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.CommentModel
import com.example.blogviewer.io.model.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BlogDetailsFragment : Fragment() {
    private var binding: FragmentBlogDetailsBinding? = null
    private lateinit var mainLooper: Looper

    companion object {
        private const val BLOG_MODEL = "model"
        fun newInstance(blogModel: BlogDetailModel): BlogDetailsFragment {
            val args = Bundle()
            args.putParcelable(BLOG_MODEL, blogModel)
            val fragment = BlogDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blog_details, container, false)
        binding = FragmentBlogDetailsBinding.bind(rootView)

        mainLooper = Looper.getMainLooper()
        GlobalScope.launch {
            displayData()
        }
        return rootView
    }

    private fun displayData() {
        val displayText: String?
        val model: BlogDetailModel? = arguments?.getParcelable(BLOG_MODEL)
        if (model != null) {
            val blogModel: BlogModel? = model.blogModel
            val userModel: UserModel? = model.userModel
            if (blogModel != null) {
                val sb = StringBuilder()
                sb.append("Blog Details\n\n\n")
                val commentModel: CommentModel? = model.commentModel
                sb.append("Comment - " + commentModel?.body + " by " + commentModel?.name + "\n\n")
                sb.append("Id - " + blogModel.id + "\n\n")
                sb.append("Title - " + blogModel.title + "\n\n")
                sb.append("Body - " + blogModel.body + "\n\n")
                sb.append("User Id - " + blogModel.userId + "\n\n")

                userModel?.let {
                    sb.append("Username - " + it.username + "\n\n")
                    sb.append("Name - " + it.name + "\n\n")
                    sb.append("Email - " + it.email + "\n\n")
                    sb.append("Phone - " + it.phone + "\n\n")
                    sb.append("Website - " + it.website + "\n\n")
                    sb.append("Address - " + it.address + "\n\n")
                    sb.append("Company - " + it.company + "\n\n")
                }

                displayText = sb.toString()
            } else {
                displayText = "Sorry. Data could not be loaded."
            }
        } else {
            displayText = "Sorry. Data could not be loaded."
        }

        Handler(mainLooper).post {
            binding?.detailsTextView?.text = displayText
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}