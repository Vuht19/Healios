package com.example.newhealios.list.diffutil

import androidx.recyclerview.widget.DiffUtil
import android.os.Bundle
import com.example.domain.model.Post

class PostDiffUtilCallback(private val oldItems: List<Post>, private val newItems: List<Post>) :
    DiffUtil.Callback() {

    companion object {
        const val EXTRA_USER_ID: String = "EXTRA_USER_ID"
        const val EXTRA_TITLE: String = "EXTRA_TITLE"
        const val EXTRA_BODY: String = "EXTRA_BODY"
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newItems[newItemPosition].id == (oldItems[oldItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newItems[newItemPosition] == oldItems[oldItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newPost: Post = newItems[newItemPosition]
        val oldPost: Post = oldItems[oldItemPosition]
        val diff = Bundle()
        if (newPost.userId != oldPost.userId) {
            diff.putInt(EXTRA_USER_ID, newPost.userId)
        }
        if (!newPost.title.equals(oldPost.title)) {
            diff.putString(EXTRA_TITLE, newPost.title)
        }
        if (!newPost.body.equals(oldPost.body)) {
            diff.putString(EXTRA_BODY, newPost.body)
        }
        return if (diff.size() == 0) {
            null
        } else diff
    }
}