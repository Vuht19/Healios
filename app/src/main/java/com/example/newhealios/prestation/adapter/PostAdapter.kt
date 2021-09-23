package com.example.healios.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healios.ui.fragment.callback.ItemPostClick
import com.example.newhealios.R
import com.example.newhealios.domain.model.Post

class PostAdapter(private val mContext: Context, private val mPostList: ArrayList<Post>?) :
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
        if (mPostList!!.isNotEmpty()) {
            val post = mPostList[position]
//            holder.tvIdUser.text = post.id.toString()
            holder.tvTitle.text = post.title
            holder.tvContent.text = post.body
            holder.itemView.setOnClickListener {
                mListener.let {
                    it!!.onClickItem(position, post)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mPostList != null && mPostList.isNotEmpty()) {
            mPostList.size
        } else 0
    }

    fun setDataList(postList: List<Post>) {
        mPostList?.clear()
        mPostList?.addAll(postList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvIdUser: TextView = itemView.findViewById(R.id.tvIdUser)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvContent: TextView = itemView.findViewById(R.id.tvContent)
    }
}