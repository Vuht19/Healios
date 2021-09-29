package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.EntityComment
import com.example.data.network.api.response.DtoComment

@Dao
interface CommentDao {
    @Insert
    fun insertAll(commentCaches: List<EntityComment?>)

    @Query("SELECT * FROM comments")
    fun getComments(): List<DtoComment>

    @Query("SELECT * FROM comments WHERE postId=:id")
    fun getCommentsById(id: Int): List<EntityComment>

    @Query("DELETE FROM comments")
    fun deleteAll()
}