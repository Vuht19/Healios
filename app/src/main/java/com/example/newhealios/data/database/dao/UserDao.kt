package com.example.newhealios.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.database.model.EntityUser

@Dao
interface UserDao {
    @Insert
    fun insertAll(userCaches: List<EntityUser?>)

    @Query("SELECT * FROM users")
    fun getUsers(): List<EntityUser>

    @Query("SELECT * FROM users WHERE idUser=:id")
    fun getUserById(id: Int): EntityUser

    @Query("DELETE FROM users")
    fun deleteAll()
}