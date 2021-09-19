package com.example.newhealios.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentCache(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var postId: Int,
    var name: String,
    var email: String,
    var body: String
)