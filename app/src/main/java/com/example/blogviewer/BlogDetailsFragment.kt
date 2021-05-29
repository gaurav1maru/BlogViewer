package com.example.blogviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class BlogDetailsFragment : Fragment() {
    companion object {
        fun newInstance(): BlogDetailsFragment {
            return BlogDetailsFragment()
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