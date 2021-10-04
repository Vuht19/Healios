package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.EntityUser

@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(userCaches: List<EntityUser?>)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<EntityUser>

    @Query("SELECT * FROM users WHERE idUser=:id")
    suspend fun getUserById(id: Int): EntityUser

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}