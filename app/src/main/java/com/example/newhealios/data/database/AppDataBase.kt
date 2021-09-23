package com.example.newhealios.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newhealios.data.database.dao.CommentDao
import com.example.newhealios.data.database.dao.PostDao
import com.example.newhealios.data.database.dao.UserDao
import com.example.newhealios.data.database.model.EntityComment
import com.example.newhealios.data.database.model.EntityPost
import com.example.newhealios.data.database.model.EntityUser

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