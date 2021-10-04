package com.example.healios.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Comment
import com.example.newhealios.R
import com.example.newhealios.detail.diffutil.CommentDiffUtilCallback

class CommentAdapter(private val mContext: Context, private val mCommentList: ArrayList<Comment>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mCommentList.isNotEmpty()) {
            val comment = mCommentList[position]
            holder.tvIdUser.text = comment.postId.toString()
            holder.tvName.text = "Name:  " + comment.name
            holder.tvContent.text = "Content:  " + comment.body
            holder.tvEmail.text = "Email:  " + comment.email
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                val comment = mCommentList.get(position)
                if (key == CommentDiffUtilCallback.EXTRA_POST_ID) {
                    holder.tvIdUser.text = comment.postId.toString()
                } else if (key == CommentDiffUtilCallback.EXTRA_EMAIL) {
                    holder.tvEmail.text = "Email:  " + comment.email
                } else if (key == CommentDiffUtilCallback.EXTRA_NAME) {
                    holder.tvName.text = "Name:  " + comment.name
                } else if (key == CommentDiffUtilCallback.EXTRA_BODY) {
                    holder.tvContent.text = "Content:  " + comment.body
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mCommentList.isNotEmpty()) {
            mCommentList.size
        } else 0
    }

    fun setDataList(comments: List<Comment>) {
        val result = DiffUtil.calculateDiff(CommentDiffUtilCallback(mCommentList, comments))
        mCommentList.clear()
        mCommentList.addAll(comments)
        result.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvIdUser: TextView = itemView.findViewById(R.id.tvIdUser)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvContent: TextView = itemView.findViewById(R.id.tvContent)
        var tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
    }
}