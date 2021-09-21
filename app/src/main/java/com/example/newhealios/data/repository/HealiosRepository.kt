package com.example.newhealios.data.repository

import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.base.safeApiCall
import com.example.newhealios.data.cache.AppDatabase
import com.example.newhealios.data.cache.model.CommentCache
import com.example.newhealios.data.cache.model.PostCache
import com.example.newhealios.data.cache.model.UserCache
import com.example.newhealios.data.cloud.api.response.CommentResponse
import com.example.newhealios.data.cloud.api.response.PostResponse
import com.example.newhealios.data.cloud.api.response.UserResponse
import com.example.newhealios.data.cloud.service.HealiosService
import com.example.newhealios.domain.mapper.Mapper
import com.example.newhealios.domain.model.Comment
import com.example.newhealios.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class HealiosRepository @Inject constructor(
    private val healiosService: HealiosService,
    private val appDatabase: AppDatabase,
    private val dispatcher: CoroutineDispatcher
) {
    /**
     * Cloud
     * */
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

    /*
    * Local
    * */
    fun savePostDataInCache(postCacheList: List<PostCache>) {
        appDatabase.postDao()?.deleteAll()
        appDatabase.postDao()?.insertAll(postCacheList)
    }

    fun getPostListFromCache(): List<PostCache>? {
        return appDatabase.postDao()?.getPostList()
    }

    fun saveUserDataInCache(userCacheList: List<UserCache>) {
        appDatabase.userDao()?.deleteAll()
        appDatabase.userDao()?.insertAll(userCacheList)
    }

    fun saveCommentDataInCache(commentList: List<CommentCache>) {
        appDatabase.commentDao()?.deleteAll()
        appDatabase.commentDao()?.insertAll(commentList)
    }

    fun getUserCacheById(id: Int): UserCache? {
        return appDatabase.userDao()?.getUserById(id)
    }

    fun getCommentListCacheById(id: Int): List<CommentCache>? {
        return appDatabase.commentDao()?.getCommentsById(id)
    }
}