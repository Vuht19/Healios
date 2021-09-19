package com.example.newhealios.data.cache.model

class PostDetailCache(
    val postCache: PostCache,
    val userCache: UserCache,
    val commentCache: List<CommentCache>
)