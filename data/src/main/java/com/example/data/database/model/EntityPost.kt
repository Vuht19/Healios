package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class EntityPost(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idPost: Int,
    var body: String,
    var title: String,
    var userId: Int,
    var totalLike: Int = 0,
)