package com.example.blogviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blogviewer.io.model.BlogDetailModel

class BlogDetailsFragment : Fragment() {
    companion object {
        private const val BLOGMODEL = "model"
        fun newInstance(blogModel: BlogDetailModel): BlogDetailsFragment {
            val args = Bundle()
            args.putParcelable(BLOGMODEL, blogModel)
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
        return inflater.inflate(R.layout.fragment_blog_details, container, false)
    }
}