package com.example.newhealios.domain.usecase

import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.repository.HealiosRepository
import com.example.newhealios.domain.mapper.Mapper
import com.example.newhealios.domain.model.Post
import com.example.newhealios.domain.model.ResultPost
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(val healiosRepository: HealiosRepository) {

    fun execute(): Flow<ResultPost<Post>> {
        return flow {
            val result = healiosRepository.getListPost()
            if (result is ResultWrapper.Success) {
                healiosRepository.savePostDataInCache(Mapper.responseToPostListCache(result.takeValueOrThrow()))
                emit(ResultPost(null, Mapper.responseToPostList(result.takeValueOrThrow())))
            } else if (result is ResultWrapper.NetworkError) {
                healiosRepository.getPostListFromCache()?.let { Mapper.cacheToPostList(it) }
                    ?.let {
                        emit(
                            ResultPost(IOException(), it)
                        )
                    }
            }
        }
    }
}