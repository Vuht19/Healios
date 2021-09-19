package com.example.newhealios.domain.usecase

import android.util.Log
import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.repository.HealiosRepository
import com.example.newhealios.domain.mapper.Mapper
import com.example.newhealios.domain.model.Comment
import com.example.newhealios.domain.model.Post
import com.example.newhealios.domain.model.PostDetail
import com.example.newhealios.domain.model.User
import kotlinx.coroutines.flow.*
import java.util.concurrent.Flow
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(private val healiosRepository: HealiosRepository) {
    fun execute(post: Post): kotlinx.coroutines.flow.Flow<PostDetail?> = flow {
        val flowUser = flowOf(healiosRepository.getListUser())
        val flowComment = flowOf(healiosRepository.getLisComment())
        var postDetail: PostDetail? = null
        var user: User
        var commentList: List<Comment>
        flowUser.zip(flowComment, { userResult, commentResult ->
            if (userResult is ResultWrapper.Success) {
                healiosRepository.saveUserDataInCache(Mapper.responseToUserListCache(userResult.value))
                Log.e("dladlaldasld: ", "userResult is ResultWrapper.Success")
            }
            if (commentResult is ResultWrapper.Success) {
                healiosRepository.saveCommentDataInCache(
                    Mapper.responseToCommentListCache(
                        commentResult.value
                    )
                )
                Log.e("dladlaldasld: ", "commentResult is ResultWrapper.Success")
            }
        }).onCompletion {
            user = Mapper.cacheToSingleUser(healiosRepository.getUserCacheById(post.userId))
            commentList =
                Mapper.cacheToCommentList(healiosRepository.getCommentListCacheById(post.userId))
            postDetail = PostDetail(post, user, commentList)
        }.collect {
            Log.e("dladlaldasld: ", "collect: $this")
        }
        emit(postDetail)
    }
}