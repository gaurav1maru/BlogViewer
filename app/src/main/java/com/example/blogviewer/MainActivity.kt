package com.example.blogviewer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blogviewer.adapter.BlogAdapter
import com.example.blogviewer.io.model.BlogDetailModel

class MainActivity : AppCompatActivity(),
    BlogAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, BlogListFragment(), "blogList").commit()
        }
    }

    override fun onItemClick(blogDetailModel: BlogDetailModel) {
        Toast.makeText(
            this, "You selected " + blogDetailModel.blogModel.title + "!",
            Toast.LENGTH_SHORT
        ).show()
        val detailsFragment =
            BlogDetailsFragment.newInstance(blogDetailModel)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, detailsFragment, "blogDetails")
            .addToBackStack(null)
            .commit()
    }
}