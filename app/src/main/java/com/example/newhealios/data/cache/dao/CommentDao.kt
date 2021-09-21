package com.example.newhealios.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.cache.model.CommentCache
import com.example.newhealios.data.cloud.api.response.CommentResponse

@Dao
interface CommentDao {
    @Insert
    fun insertAll(commentCaches: List<CommentCache?>)

    @Query("SELECT * FROM comments")
    fun getComments(): List<CommentResponse>

    @Query("SELECT * FROM comments WHERE postId=:id")
    fun getCommentsById(id: Int): List<CommentCache>

    @Query("DELETE FROM comments")
    fun deleteAll()
}