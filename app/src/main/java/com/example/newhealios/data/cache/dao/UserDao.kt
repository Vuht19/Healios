package com.example.newhealios.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.cache.model.UserCache

@Dao
interface UserDao {
    @Insert
    fun insertAll(userCaches: List<UserCache?>)

    @Query("SELECT * FROM users")
    fun getUsers(): List<UserCache>

    @Query("SELECT * FROM users WHERE idUser=:id")
    fun getUserById(id: Int): UserCache

}