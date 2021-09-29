package com.example.domain.mapper

import com.example.data.database.model.*
import com.example.data.network.api.response.*
import com.example.domain.model.*
import java.util.ArrayList

object Mapper {

    fun dtoPostsToPostList(postResponseList: List<DtoPost>): List<Post> {
        val postList = ArrayList<Post>()
        postResponseList.map {
            postList.add(dtoPostToSinglePost(it))
        }
        return postList
    }

    private fun dtoPostToSinglePost(postResponse: DtoPost?): Post {
        return Post(
            body = postResponse?.body.orEmpty(),
            title = postResponse?.title.orEmpty(),
            userId = postResponse?.userId ?: 0
        )
    }

    fun dtoPostToListEntityPost(postResponseList: List<DtoPost>): List<EntityPost> {
        val postList = ArrayList<EntityPost>()
        postResponseList.map {
            postList.add(dtoPostToSingleEntityPost(it))
        }
        return postList
    }

    private fun dtoPostToSingleEntityPost(postResponse: DtoPost?): EntityPost {
        return EntityPost(
            body = postResponse?.body.orEmpty(),
            title = postResponse?.title.orEmpty(),
            userId = postResponse?.userId ?: 0
        )
    }

    fun entityPostsToPostList(postListCache: List<EntityPost>): List<Post> {
        val postList = ArrayList<Post>()
        postListCache.map {
            postList.add(entityPostToSinglePost(it))
        }
        return postList
    }

    private fun entityPostToSinglePost(postCache: EntityPost?): Post {
        return Post(
            body = postCache?.body.orEmpty(),
            title = postCache?.title.orEmpty(),
            userId = postCache?.userId ?: 0
        )
    }

    fun dtoUserToListEntityUser(userResponseList: List<DtoUser>): List<EntityUser> {
        val userList = ArrayList<EntityUser>()
        userResponseList.map {
            userList.add(dtoToSingleEntityUser(it))
        }
        return userList
    }

    private fun dtoToSingleEntityUser(userResponse: DtoUser?): EntityUser {
        return EntityUser(
            idLocal = null,
            idUser = userResponse?.idUser ?: 0,
            name = userResponse?.name.orEmpty(),
            username = userResponse?.username.orEmpty(),
            email = userResponse?.email.orEmpty(),
            phone = userResponse?.phone.orEmpty(),
            website = userResponse?.website.orEmpty(),
            entityAddress = dtoAddressToSingleEntityAddress(userResponse?.address),
            companyCache = dtoCompanyToSingleEntityCompany(userResponse?.company),
        )
    }


    private fun dtoCompanyToSingleEntityCompany(companyResponse: DtoCompany?): EntityCompany {
        return EntityCompany(
            catchPhrase = companyResponse?.catchPhrase.orEmpty(),
            nameCompany = companyResponse?.nameCompany.orEmpty(),
            bs = companyResponse?.bs.orEmpty()
        )
    }

    private fun dtoAddressToSingleEntityAddress(addressResponse: DtoAddress?): EntityAddress {
        return EntityAddress(
            street = addressResponse?.street.orEmpty(),
            suite = addressResponse?.suite.orEmpty(),
            zipcode = addressResponse?.zipcode.orEmpty(),
            geoCache = dtoGeoToSingleEntityGeo(addressResponse?.geo),
        )
    }

    private fun dtoGeoToSingleEntityGeo(geoResponse: DtoGeo?): EntityGeo {
        return EntityGeo(
            lat = geoResponse?.lat.orEmpty(),
            lng = geoResponse?.lng.orEmpty()
        )
    }

    fun dtoCommentsToListEntityComment(commentResponseList: List<DtoComment>): List<EntityComment> {
        val userList = ArrayList<EntityComment>()
        commentResponseList.map {
            userList.add(dtoCommentToSingleEntityComment(it))
        }
        return userList
    }

    private fun dtoCommentToSingleEntityComment(commentResponse: DtoComment): EntityComment {
        return EntityComment(
            id = 0,
            postId = commentResponse.postId ?: 0,
            name = commentResponse.name.orEmpty(),
            email = commentResponse.email.orEmpty(),
            body = commentResponse.body.orEmpty()
        )
    }

    fun entityUserToSingleUser(user: EntityUser?): User {
        return User(
            idUser = user?.idUser ?: 0,
            name = user?.name.orEmpty(),
            username = user?.username.orEmpty(),
            email = user?.email.orEmpty(),
            phone = user?.phone.orEmpty(),
            website = user?.website.orEmpty(),
            address = entityAddressToSingleAddress(user?.entityAddress),
            company = entityCompanyToSingleCompany(user?.companyCache),
        )
    }

    private fun entityAddressToSingleAddress(entityAddress: EntityAddress?): Address {
        return Address(
            street = entityAddress?.street.orEmpty(),
            suite = entityAddress?.suite.orEmpty(),
            zipcode = entityAddress?.zipcode.orEmpty(),
            geo = entityGeoToSingleGeo(entityAddress?.geoCache),
        )
    }

    private fun entityGeoToSingleGeo(geoCache: EntityGeo?): Geo {
        return Geo(
            lat = geoCache?.lat.orEmpty(),
            lng = geoCache?.lng.orEmpty()
        )
    }

    private fun entityCompanyToSingleCompany(companyCache: EntityCompany?): Company {
        return Company(
            nameCompany = companyCache?.nameCompany.orEmpty(),
            catchPhrase = companyCache?.catchPhrase.orEmpty(),
            bs = companyCache?.bs.orEmpty(),
        )
    }

    fun entityCommentsToCommentList(commentCacheList: List<EntityComment>?): List<Comment> {
        val commentList = ArrayList<Comment>()
        commentCacheList?.map {
            commentList.add(entityCommentToSingleComment(it))
        }
        return commentList
    }

    private fun entityCommentToSingleComment(commentCache: EntityComment?): Comment {
        return Comment(
            postId = commentCache?.postId ?: 0,
            name = commentCache?.name.orEmpty(),
            email = commentCache?.email.orEmpty(),
            body = commentCache?.body.orEmpty(),
        )
    }
}