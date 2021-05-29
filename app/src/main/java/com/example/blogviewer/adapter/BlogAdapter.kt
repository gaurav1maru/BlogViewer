package com.example.blogviewer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogviewer.R
import com.example.blogviewer.io.model.BlogDetailModel

class BlogAdapter(
    val context: Context,
    private var list: List<BlogDetailModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    private var mSectionPositions: ArrayList<Int>? = null

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var blogTitleTextView: AppCompatTextView = v.findViewById(R.id.blogTitleTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_blog, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogDetailModel = list[position]

        holder.blogTitleTextView.text = blogDetailModel.blogModel.title
        holder.itemView.setOnClickListener { listener.onItemClick(blogDetailModel) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(blogDetailModel: BlogDetailModel)
    }
}