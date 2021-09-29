package com.example.domain.model

data class Comment(
    var postId: Int,
    var name: String,
    var email: String,
    var body: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Comment
        if (postId != other.postId) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (body != other.body) return false

        return true
    }
}