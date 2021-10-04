package com.example.domain.usecase

import com.example.data.base.ResultWrapper
import com.example.data.repository.HealiosRepository
import com.example.domain.mapper.Mapper
import com.example.domain.model.Post
import com.example.domain.model.PostResult
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(val healiosRepository: HealiosRepository) {

    fun execute(): Flow<ResultWrapper<PostResult>> {
        return flow {
            val postListFromDatabase = healiosRepository.getPostListFromDatabase()
            postListFromDatabase?.let {
                emit(ResultWrapper.Success(PostResult(Mapper.entityPostsToPostList(it))))
            }
            val wrapper = healiosRepository.getListPost()
            val wrapperValue = wrapper.takeValueOrThrow()
            if (!wrapperValue.isNullOrEmpty()) {
                val result = Mapper.dtoPostToListEntityPost(wrapperValue)
                if (postListFromDatabase.isNullOrEmpty() && result.isNullOrEmpty()) {
                    emit(ResultWrapper.EmptyData(null))
                } else if (postListFromDatabase.isNullOrEmpty()) {
                    emit(ResultWrapper.Success(PostResult(Mapper.dtoPostsToPostList(wrapperValue))))
                    healiosRepository.savePostInDatabase(result)
                }
            }
        }.onStart { emit(ResultWrapper.Loading) }
            .catch {
                if (it is IOException) {
                    emit(ResultWrapper.NetworkError)
                } else {
                    emit(ResultWrapper.GenericError(it))
                }
            }
    }
}