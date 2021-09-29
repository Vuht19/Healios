package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.CommentDao
import com.example.data.database.dao.PostDao
import com.example.data.database.dao.UserDao
import com.example.data.database.model.EntityComment
import com.example.data.database.model.EntityPost
import com.example.data.database.model.EntityUser

@Database(
    entities = [EntityPost::class, EntityUser::class, EntityComment::class],
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