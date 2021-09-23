package com.example.newhealios.domain.usecase

import com.example.newhealios.data.base.ResultWrapper
import com.example.newhealios.data.repository.HealiosRepository
import com.example.newhealios.domain.mapper.Mapper
import com.example.newhealios.domain.model.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(private val healiosRepository: HealiosRepository) {
    fun execute(post: Post): Flow<PostDetail> {
        return flow {
            var postDetail: PostDetail?
            var user: User?
            var commentList: List<Comment>?

            user =
                Mapper.entityUserToSingleUser(healiosRepository.getUserFromDatabaseById(post.userId))
            commentList =
                Mapper.entityCommentsToCommentList(
                    healiosRepository.getCommentListFromDatabaseById(
                        post.userId
                    )
                )
            postDetail = PostDetail(post, user, commentList)
            emit(postDetail)

            val flowUser = flowOf(healiosRepository.getListUser())
            val flowComment = flowOf(healiosRepository.getLisComment())

            flowUser.zip(flowComment) { userResult, commentResult ->
                if (userResult is ResultWrapper.Success) {
                    healiosRepository.saveUserDataInDatabase(
                        Mapper.dtoUserToListEntityUser(
                            userResult.value
                        )
                    )
                }
                if (commentResult is ResultWrapper.Success) {
                    healiosRepository.saveCommentDataInDatabase(
                        Mapper.dtoCommentsToListEntityComment(
                            commentResult.value
                        )
                    )
                }
            }.onCompletion {
                user =
                    Mapper.entityUserToSingleUser(healiosRepository.getUserFromDatabaseById(post.userId))
                commentList =
                    Mapper.entityCommentsToCommentList(
                        healiosRepository.getCommentListFromDatabaseById(
                            post.userId
                        )
                    )
                postDetail = PostDetail(post, user!!, commentList!!)
                postDetail?.let { it -> emit(it) }
            }.collect()
        }
    }
}