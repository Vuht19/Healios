package com.example.newhealios.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newhealios.data.cache.dao.CommentDao
import com.example.newhealios.data.cache.dao.PostDao
import com.example.newhealios.data.cache.dao.UserDao
import com.example.newhealios.data.cache.model.CommentCache
import com.example.newhealios.data.cache.model.PostCache
import com.example.newhealios.data.cache.model.UserCache

@Database(
    entities = [PostCache::class, UserCache::class, CommentCache::class],
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