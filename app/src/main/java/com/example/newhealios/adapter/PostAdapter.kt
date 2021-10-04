package com.example.healios.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhealios.R
import com.example.newhealios.list.diffutil.PostDiffUtilCallback

import android.os.Bundle
import com.example.domain.model.Post
import com.example.newhealios.callback.ItemPostClick
import com.example.newhealios.list.diffutil.PostDiffUtilCallback.Companion.EXTRA_BODY
import com.example.newhealios.list.diffutil.PostDiffUtilCallback.Companion.EXTRA_TITLE


class PostAdapter(private val mContext: Context, private val mPostList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var mListener: ItemPostClick? = null
    fun seListener(listener: ItemPostClick?) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mPostList.isNotEmpty()) {
            val post = mPostList[position]
            holder.tvTitle.text = post.title
            holder.tvContent.text = post.body
            holder.itemView.setOnClickListener {
                mListener.let {
                    it!!.onClickItem(position, post)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                val post = mPostList.get(position)
                if (key == EXTRA_TITLE) {
                    holder.tvTitle.text = post.title
                } else if (key == EXTRA_BODY) {
                    holder.tvContent.text = post.body
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mPostList.isNotEmpty()) {
            mPostList.size
        } else 0
    }

    fun setDataList(postList: List<Post>) {
        val result = DiffUtil.calculateDiff(PostDiffUtilCallback(mPostList, postList))
        mPostList.clear()
        mPostList.addAll(postList)
        result.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvContent: TextView = itemView.findViewById(R.id.tvContent)
    }
}