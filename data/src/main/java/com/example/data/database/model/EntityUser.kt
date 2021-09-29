package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.database.model.EntityAddress
import com.example.data.database.model.EntityCompany

@Entity(tableName = "users")
data class EntityUser(
    @PrimaryKey(autoGenerate = true)
    var idLocal: Int?,
    var idUser: Int?,
    var name: String?,
    var username: String?,
    var email: String?,
    var phone: String?,
    var website: String?,
    @Embedded
    var entityAddress: EntityAddress?,
    @Embedded
    var companyCache: EntityCompany?,
)
