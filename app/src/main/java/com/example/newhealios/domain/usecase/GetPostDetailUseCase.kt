package com.example.newhealios.domain.usecase

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.repository.HealiosRepository
import com.example.newhealios.domain.mapper.Mapper
import com.example.newhealios.domain.model.*
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(private val healiosRepository: HealiosRepository) {
    fun execute(post: Post): kotlinx.coroutines.flow.Flow<ResultPostDetail<out PostDetail>> {
        return flow {
            val flowUser = flowOf(healiosRepository.getListUser())
            val flowComment = flowOf(healiosRepository.getLisComment())
            var postDetail: PostDetail? = null
            var user: User
            var commentList: List<Comment>
            var throwable: Throwable? = null
            flowUser.zip(flowComment, { userResult, commentResult ->
                if (userResult is ResultWrapper.Success) {
                    healiosRepository.saveUserDataInCache(Mapper.responseToUserListCache(userResult.value))
                } else if (userResult is ResultWrapper.NetworkError) {
                    throwable = IOException()
                }
                if (commentResult is ResultWrapper.Success) {
                    healiosRepository.saveCommentDataInCache(
                        Mapper.responseToCommentListCache(
                            commentResult.value
                        )
                    )
                } else if (userResult is ResultWrapper.NetworkError) {
                    throwable = IOException()
                }
            }).onCompletion {
                user = Mapper.cacheToSingleUser(healiosRepository.getUserCacheById(post.userId))
                commentList =
                    Mapper.cacheToCommentList(healiosRepository.getCommentListCacheById(post.userId))
                postDetail = PostDetail(post, user, commentList)
                if (user == null || commentList == null) {
                    emit(ResultPostDetail(throwable, null))
                } else {
                    emit(ResultPostDetail(throwable, postDetail))
                }
            }.collect { }
        }
    }
}