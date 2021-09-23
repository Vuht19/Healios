package com.example.newhealios.domain.usecase

import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.repository.HealiosRepository
import com.example.newhealios.domain.mapper.Mapper
import com.example.newhealios.domain.model.Post
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(val healiosRepository: HealiosRepository) {

    fun execute(): Flow<List<Post>> {
        return flow {
            val postListFromDatabase = healiosRepository.getPostListFromDatabase()
            postListFromDatabase?.let { emit(Mapper.entityPostsToPostList(it)) }
            val result = healiosRepository.getListPost()
            if (result is ResultWrapper.Success) {
                healiosRepository.savePostInDatabase(Mapper.dtoPostToListEntityPost(result.takeValueOrThrow()))
                emit(Mapper.dtoPostsToPostList(result.takeValueOrThrow()))
            }
        }
    }
}