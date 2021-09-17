package com.example.newhealios.data.cloud.api.response

class PostDetailResponse(
    val postResponse: PostResponse?,
    val user: UserResponse?,
    val commentResponses: List<CommentResponse>?
)