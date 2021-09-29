package com.example.newhealios.detail

import androidx.recyclerview.widget.DiffUtil
import android.os.Bundle
import com.example.domain.model.Comment

class CommentDiffUtilCallback(
    private val oldItems: List<Comment>,
    private val newItems: List<Comment>,
) :
    DiffUtil.Callback() {

    companion object {
        const val EXTRA_POST_ID: String = "EXTRA_POST_ID"
        const val EXTRA_NAME: String = "EXTRA_NAME"
        const val EXTRA_EMAIL: String = "EXTRA_EMAIL"
        const val EXTRA_BODY: String = "EXTRA_BODY"
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newItems[newItemPosition].postId == (oldItems[oldItemPosition].postId)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newItems[newItemPosition] == oldItems[oldItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newComment: Comment = newItems[newItemPosition]
        val oldComment: Comment = oldItems[oldItemPosition]
        val diff = Bundle()
        if (newComment.postId != oldComment.postId) {
            diff.putInt(EXTRA_POST_ID, newComment.postId)
        }
        if (!newComment.email.equals(oldComment.email)) {
            diff.putString(EXTRA_EMAIL, newComment.email)
        }
        if (!newComment.name.equals(oldComment.name)) {
            diff.putString(EXTRA_BODY, newComment.name)
        }
        if (!newComment.body.equals(oldComment.body)) {
            diff.putString(EXTRA_BODY, newComment.body)
        }
        return if (diff.size() == 0) {
            null
        } else diff
    }
}