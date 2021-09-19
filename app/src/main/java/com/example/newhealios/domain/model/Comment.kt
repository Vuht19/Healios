package com.example.newhealios.domain.model

data class Comment(
    var postId: Int,
    var name: String,
    var email: String,
    var body: String
)