package com.example.domain.model

class PostDetail(
    val post: Post,
    val user: User,
    val comments: List<Comment>
)