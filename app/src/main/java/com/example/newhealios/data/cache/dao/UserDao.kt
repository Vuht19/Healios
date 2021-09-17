package com.example.newhealios.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.cache.model.User
import com.example.newhealios.data.cloud.api.response.UserResponse

@Dao
interface UserDao {
    @Insert
    fun insertAll(users: List<User?>)

    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE idUser=:id")
    fun getUserById(id: Int): User

}