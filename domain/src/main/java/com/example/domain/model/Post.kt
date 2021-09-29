package com.example.domain.model

import java.io.Serializable

data class Post(
    var id: Int,
    var body: String,
    var title: String,
    var userId: Int,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Post
        if (body != other.body) return false
        if (title != other.title) return false
        if (userId != other.userId) return false

        return true
    }
}
