package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.dao.UserDao
import com.example.data.database.AppDatabase
import com.example.data.database.AppDatabase.Companion.MIGRATION_1_TO_2
import com.example.data.database.dao.CommentDao
import com.example.data.database.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).addMigrations(MIGRATION_1_TO_2).build()
    }
}