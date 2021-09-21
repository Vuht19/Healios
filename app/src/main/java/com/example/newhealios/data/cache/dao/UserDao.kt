package com.example.newhealios.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newhealios.data.cache.model.UserCache
import retrofit2.http.DELETE

@Dao
interface UserDao {
    @Insert
    fun insertAll(userCaches: List<UserCache?>)

    @Query("SELECT * FROM users")
    fun getUsers(): List<UserCache>

    @Query("SELECT * FROM users WHERE idUser=:id")
    fun getUserById(id: Int): UserCache

    @Query("DELETE FROM users")
    fun deleteAll()
}