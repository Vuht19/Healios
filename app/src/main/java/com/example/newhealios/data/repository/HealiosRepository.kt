package com.example.newhealios.data.repository

import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.base.safeApiCall
import com.example.newhealios.data.cache.model.Post
import com.example.newhealios.data.cloud.api.response.CommentResponse
import com.example.newhealios.data.cloud.api.response.PostResponse
import com.example.newhealios.data.cloud.api.response.UserResponse
import com.example.newhealios.data.cloud.service.HealiosService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HealiosRepository @Inject constructor(
    private val healiosService: HealiosService,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getListUser(): ResultWrapper<List<UserResponse>> {
        return safeApiCall(dispatcher) {
            healiosService.getUsers()
        }
    }

    suspend fun getLisComment(): ResultWrapper<List<CommentResponse>> {
        return safeApiCall(dispatcher) {
            healiosService.getComments()
        }
    }

    suspend fun getListPost(): ResultWrapper<List<PostResponse>> {
        return safeApiCall(dispatcher) {
            healiosService.getPost()
        }
    }
}