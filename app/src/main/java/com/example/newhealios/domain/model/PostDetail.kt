package com.example.newhealios.domain.model

import com.example.newhealios.data.cloud.api.response.CommentResponse

class PostDetail(
    val post: Post,
    val user: User,
    val comments: List<Comment>
)