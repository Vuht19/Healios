package com.example.newhealios.data.cloud.service

import com.example.newhealios.data.cloud.api.response.CommentResponse
import com.example.newhealios.data.cloud.api.response.PostResponse
import com.example.newhealios.data.cloud.api.response.UserResponse
import retrofit2.http.GET

interface HealiosService {

    @GET("posts")
    suspend fun getPost(): List<PostResponse>

    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("comments")
    suspend fun getComments(): List<CommentResponse>
}