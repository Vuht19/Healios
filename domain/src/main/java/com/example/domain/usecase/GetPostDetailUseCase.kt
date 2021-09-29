package com.example.domain.usecase

import com.example.data.repository.HealiosRepository
import com.example.domain.mapper.Mapper
import com.example.domain.model.Comment
import com.example.domain.model.Post
import com.example.domain.model.PostDetail
import com.example.domain.model.User
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(private val healiosRepository: HealiosRepository) {
    fun execute(post: Post): Flow<PostDetail> {
        return flow {
            val postDetail: PostDetail?
            val user: User?
            val commentList: List<Comment>?

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
        }
    }

    fun getPostDetailFromNetwork(post: Post): Flow<PostDetail> {
        return flow {
            val flowUser = flowOf(healiosRepository.getListUser())
            val flowComment = flowOf(healiosRepository.getLisComment())

            var user: User?
            var commentList: List<Comment>?

            flowUser.zip(flowComment) { userResult, commentResult ->
                healiosRepository.saveUserDataInDatabase(
                    Mapper.dtoUserToListEntityUser(
                        userResult.takeValueOrThrow()
                    )
                )

                healiosRepository.saveCommentDataInDatabase(
                    Mapper.dtoCommentsToListEntityComment(
                        commentResult.takeValueOrThrow()
                    )
                )
            }.onCompletion {
                user =
                    Mapper.entityUserToSingleUser(healiosRepository.getUserFromDatabaseById(post.userId))
                commentList =
                    Mapper.entityCommentsToCommentList(
                        healiosRepository.getCommentListFromDatabaseById(
                            post.userId
                        )
                    )
                emit(PostDetail(post, user!!, commentList!!))
            }.collect()
        }
    }
}