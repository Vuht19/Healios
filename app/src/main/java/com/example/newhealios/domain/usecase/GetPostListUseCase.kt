package com.example.newhealios.domain.usecase

import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.repository.HealiosRepository
import com.example.newhealios.domain.mapper.Mapper
import com.example.newhealios.domain.model.Post
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(val healiosRepository: HealiosRepository) {

    fun execute(): Flow<List<Post>> = flow {
        val result = healiosRepository.getListPost()
        if (result is ResultWrapper.Success) {
            healiosRepository.savePostDataInCache(Mapper.responseToPostListCache(result.takeValueOrThrow()))
            emit(Mapper.responseToPostList(result.takeValueOrThrow()))
        } else {
            emit(Mapper.responseToPostList(result.takeValueOrThrow()))
            healiosRepository.getPostListFromCache()?.let { Mapper.cacheToPostList(it) }
                ?.let {
                    emit(it)
                }
        }
    }
}