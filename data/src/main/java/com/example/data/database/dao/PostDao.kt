package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.EntityPost

@Dao
interface PostDao {
    @Insert
    suspend fun insertAll(postCaches: List<EntityPost>)

    @Query("SELECT * FROM posts")
    suspend fun getPostList(): List<EntityPost>

    @Query("DELETE FROM posts")
    suspend fun deleteAll()
}