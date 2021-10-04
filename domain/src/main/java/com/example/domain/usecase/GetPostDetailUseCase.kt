package com.example.domain.usecase

import com.example.data.base.ResultWrapper
import com.example.data.network.api.response.DtoComment
import com.example.data.network.api.response.DtoUser
import com.example.data.repository.HealiosRepository
import com.example.domain.mapper.Mapper
import com.example.domain.model.Comment
import com.example.domain.model.Post
import com.example.domain.model.PostDetail
import com.example.domain.model.User
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(private val healiosRepository: HealiosRepository) {
    fun execute(post: Post): Flow<ResultWrapper<PostDetail>> {
        return flow<ResultWrapper<PostDetail>> {
            var user: User?
            var commentList: List<Comment>?
            var postDetail: PostDetail?
            user =
                Mapper.entityUserToSingleUser(healiosRepository.getUserFromDatabaseById(post.userId))
            commentList =
                Mapper.entityCommentsToCommentList(
                    healiosRepository.getCommentListFromDatabaseById(
                        post.userId
                    )
                )
            if (user != null && !commentList.isNullOrEmpty()) {
                postDetail = PostDetail(post, user, commentList)
                postDetail.let {
                    emit(ResultWrapper.Success(postDetail!!))
                }
            }
            val flowUser = flowOf(healiosRepository.getListUser())
            val flowComment = flowOf(healiosRepository.getLisComment())

            var dtoUserList: List<DtoUser>? = null
            var dtoCommentList: List<DtoComment>? = null
            flowUser.zip(flowComment) { userWrapper, commentWrapper ->
                dtoUserList = userWrapper.takeValueOrThrow()
                dtoCommentList = commentWrapper.takeValueOrThrow()
            }.onCompletion {
                if (!dtoUserList.isNullOrEmpty()) healiosRepository.saveUserDataInDatabase(Mapper.dtoUserToListEntityUser(
                    dtoUserList!!))
                if (!dtoCommentList.isNullOrEmpty()) healiosRepository.saveCommentDataInDatabase(
                    Mapper.dtoCommentsToListEntityComment(dtoCommentList!!))
                user =
                    Mapper.entityUserToSingleUser(healiosRepository.getUserFromDatabaseById(post.userId))
                commentList =
                    Mapper.entityCommentsToCommentList(
                        healiosRepository.getCommentListFromDatabaseById(
                            post.userId
                        )
                    )
                if (user != null && !commentList.isNullOrEmpty()) {
                    postDetail = PostDetail(post, user!!, commentList!!)
                    postDetail.let {
                        emit(ResultWrapper.Success(postDetail!!))
                    }
                }
            }.collect()
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