package com.example.newhealios.data.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var idLocal: Int?,
    var idUser: Int?,
    var name: String?,
    var username: String?,
    var email: String?,
    var phone: String?,
    var website: String?,
    @Embedded
    var address: Address?,
    @Embedded
    var company: Company?,
)
