package com.example.data.network.service

import com.example.data.network.api.response.DtoComment
import com.example.data.network.api.response.DtoPost
import com.example.data.network.api.response.DtoUser
import retrofit2.http.GET

interface HealiosService {

    @GET("posts")
    suspend fun getPost(): List<DtoPost>

    @GET("users")
    suspend fun getUsers(): List<DtoUser>

    @GET("comments")
    suspend fun getComments(): List<DtoComment>
}