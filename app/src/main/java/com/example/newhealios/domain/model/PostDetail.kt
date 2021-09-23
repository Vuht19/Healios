package com.example.newhealios.domain.model

class PostDetail(
    val post: Post,
    val user: User,
    val comments: List<Comment>
)