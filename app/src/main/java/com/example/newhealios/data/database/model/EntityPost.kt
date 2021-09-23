package com.example.newhealios.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class EntityPost(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var body: String,
    var title: String,
    var userId: Int
)