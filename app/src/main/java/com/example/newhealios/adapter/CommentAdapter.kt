package com.example.newhealios.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newhealios.R
import com.example.domain.model.Comment

class CommentAdapter(private val mContext: Context, private val mCommentList: ArrayList<Comment>?) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mCommentList!!.isNotEmpty()) {
            val comment = mCommentList[position]
            holder.tvIdUser.text = comment.postId.toString()
            holder.tvName.text = "Name:  " + comment.name
            holder.tvContent.text = "Content:  " + comment.body
            holder.tvEmail.text = "Email:  " + comment.email
        }
    }

    override fun getItemCount(): Int {
        return if (mCommentList != null && mCommentList.isNotEmpty()) {
            mCommentList.size
        } else 0
    }

    fun setDataList(comments: List<Comment>) {
        mCommentList?.clear()
        mCommentList?.addAll(comments)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvIdUser: TextView = itemView.findViewById(R.id.tvIdUser)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvContent: TextView = itemView.findViewById(R.id.tvContent)
        var tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
    }
}