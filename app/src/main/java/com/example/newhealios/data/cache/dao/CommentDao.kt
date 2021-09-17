package com.example.newhealios.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.cache.model.Comment
import com.example.newhealios.data.cloud.api.response.CommentResponse

@Dao
interface CommentDao {
    @Insert
    fun insertAll(comments: List<Comment?>)

    @Query("SELECT * FROM comments")
    fun getComments(): List<CommentResponse>

    @Query("SELECT * FROM comments WHERE postId=:id")
    fun getCommentsById(id: Int): List<Comment>
}