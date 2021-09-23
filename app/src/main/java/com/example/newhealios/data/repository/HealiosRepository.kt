package com.example.newhealios.data.repository

import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.base.safeApiCall
import com.example.newhealios.data.database.AppDatabase
import com.example.newhealios.data.database.model.EntityComment
import com.example.newhealios.data.database.model.EntityPost
import com.example.newhealios.data.database.model.EntityUser
import com.example.newhealios.data.network.api.response.DtoComment
import com.example.newhealios.data.network.api.response.DtoPost
import com.example.newhealios.data.network.api.response.DtoUser
import com.example.newhealios.data.network.service.HealiosService
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
    suspend fun getListUser(): ResultWrapper<List<DtoUser>> {
        return safeApiCall(dispatcher) {
            healiosService.getUsers()
        }
    }

    suspend fun getLisComment(): ResultWrapper<List<DtoComment>> {
        return safeApiCall(dispatcher) {
            healiosService.getComments()
        }
    }

    suspend fun getListPost(): ResultWrapper<List<DtoPost>> {
        return safeApiCall(dispatcher) {
            healiosService.getPost()
        }
    }

    /*
    * Local
    * */
    fun savePostInDatabase(postCacheList: List<EntityPost>) {
        if (!postCacheList.isNullOrEmpty()) {
            appDatabase.postDao()?.deleteAll()
            appDatabase.postDao()?.insertAll(postCacheList)
        }
    }

    fun getPostListFromDatabase(): List<EntityPost>? {
        return appDatabase.postDao()?.getPostList()
    }

    fun saveUserDataInDatabase(userCacheList: List<EntityUser>) {
        if (!userCacheList.isNullOrEmpty()) {
            appDatabase.userDao()?.deleteAll()
            appDatabase.userDao()?.insertAll(userCacheList)
        }
    }

    fun saveCommentDataInDatabase(commentList: List<EntityComment>) {
        if (!commentList.isNullOrEmpty()) {
            appDatabase.commentDao()?.deleteAll()
            appDatabase.commentDao()?.insertAll(commentList)
        }
    }

    fun getUserFromDatabaseById(id: Int): EntityUser? {
        return appDatabase.userDao()?.getUserById(id)
    }

    fun getCommentListFromDatabaseById(id: Int): List<EntityComment>? {
        return appDatabase.commentDao()?.getCommentsById(id)
    }
}