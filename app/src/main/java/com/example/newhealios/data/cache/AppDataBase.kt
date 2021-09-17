package com.example.newhealios.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newhealios.data.cloud.api.response.CommentResponse
import com.example.newhealios.data.cloud.api.response.PostResponse
import com.example.newhealios.data.cache.dao.CommentDao
import com.example.newhealios.data.cache.dao.PostDao
import com.example.newhealios.data.cache.dao.UserDao
import com.example.newhealios.data.cache.model.Comment
import com.example.newhealios.data.cache.model.Post
import com.example.newhealios.data.cache.model.User
import com.example.newhealios.data.cloud.api.response.UserResponse

@Database(
    entities = [Post::class, User::class, Comment::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME: String = "helios.db"
    }

    abstract fun postDao(): PostDao?
    abstract fun userDao(): UserDao?
    abstract fun commentDao(): CommentDao?
}