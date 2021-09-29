package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.CommentDao
import com.example.data.database.dao.PostDao
import com.example.data.database.dao.UserDao
import com.example.data.database.model.EntityComment
import com.example.data.database.model.EntityPost
import com.example.data.database.model.EntityUser
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [EntityPost::class, EntityUser::class, EntityComment::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME: String = "helios.db"

        val MIGRATION_1_TO_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE posts ADD COLUMN totalLike INTEGER  NOT NULL DEFAULT 0")
            }
        }
    }

    abstract fun postDao(): PostDao?
    abstract fun userDao(): UserDao?
    abstract fun commentDao(): CommentDao?
}