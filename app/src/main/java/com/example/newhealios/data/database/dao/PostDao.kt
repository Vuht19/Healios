package com.example.newhealios.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.database.model.EntityPost

@Dao
interface PostDao {
    @Insert
    fun insertAll(postCaches: List<EntityPost>)

    @Query("SELECT * FROM posts")
    fun getPostList(): List<EntityPost>

    @Query("DELETE FROM posts")
    fun deleteAll()
}