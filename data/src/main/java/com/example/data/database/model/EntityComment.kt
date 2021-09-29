package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class EntityComment(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var postId: Int,
    var name: String,
    var email: String,
    var body: String
)