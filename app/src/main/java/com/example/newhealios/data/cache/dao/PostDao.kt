package com.example.newhealios.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.cache.model.Post
import com.example.newhealios.data.cloud.api.response.PostResponse

@Dao
interface PostDao {
    @Insert
    fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM posts")
    fun getPostList(): List<Post>

}