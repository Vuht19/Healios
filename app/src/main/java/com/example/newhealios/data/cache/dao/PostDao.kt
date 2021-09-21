package com.example.newhealios.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.cache.model.PostCache

@Dao
interface PostDao {
    @Insert
    fun insertAll(postCaches: List<PostCache>)

    @Query("SELECT * FROM posts")
    fun getPostList(): List<PostCache>

    @Query("DELETE FROM posts")
    fun deleteAll()
}