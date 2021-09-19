package com.example.newhealios.domain.mapper

import com.example.newhealios.data.cache.model.*
import com.example.newhealios.data.cloud.api.response.*
import com.example.newhealios.domain.model.*
import java.util.ArrayList

object Mapper {

    /**
     * User
     * */
    private fun responseToSingleUser(userResponse: UserResponse?): User {
        return User(
            idUser = userResponse?.idUser ?: 0,
            name = userResponse?.name.orEmpty(),
            username = userResponse?.username.orEmpty(),
            email = userResponse?.email.orEmpty(),
            phone = userResponse?.phone.orEmpty(),
            website = userResponse?.website.orEmpty(),
            address = toSingleAddress(userResponse?.address),
            company = responseToSingleCompany(userResponse?.company)
        )
    }

    private fun toSingleAddress(addressResponse: AddressResponse?): Address {
        return Address(
            street = addressResponse?.street.orEmpty(),
            suite = addressResponse?.suite.orEmpty(),
            zipcode = addressResponse?.zipcode.orEmpty(),
            geo = responseToSingleGeo(addressResponse?.geo),
        )
    }

    private fun responseToSingleCompany(companyResponse: CompanyResponse?): Company {
        return Company(
            nameCompany = companyResponse?.nameCompany.orEmpty(),
            catchPhrase = companyResponse?.catchPhrase.orEmpty(),
            bs = companyResponse?.bs.orEmpty(),
        )
    }

    private fun responseToSingleGeo(geoResponse: GeoResponse?): Geo {
        return Geo(
            lat = geoResponse?.lat.orEmpty(),
            lng = geoResponse?.lng.orEmpty()
        )
    }

    fun responseToPostList(postResponseList: List<PostResponse>): List<Post> {
        val postList = ArrayList<Post>()
        postResponseList.map {
            postList.add(responseToSinglePost(it))
        }
        return postList
    }

    private fun responseToSinglePost(postResponse: PostResponse?): Post {
        return Post(
            body = postResponse?.body.orEmpty(),
            title = postResponse?.title.orEmpty(),
            userId = postResponse?.userId ?: 0
        )
    }

    fun responseToPostListCache(postResponseList: List<PostResponse>): List<PostCache> {
        val postList = ArrayList<PostCache>()
        postResponseList.map {
            postList.add(responseToSinglePostCache(it))
        }
        return postList
    }

    private fun responseToSinglePostCache(postResponse: PostResponse?): PostCache {
        return PostCache(
            body = postResponse?.body.orEmpty(),
            title = postResponse?.title.orEmpty(),
            userId = postResponse?.userId ?: 0
        )
    }

    fun cacheToPostList(postListCache: List<PostCache>): List<Post> {
        val postList = ArrayList<Post>()
        postListCache.map {
            postList.add(cacheToSinglePost(it))
        }
        return postList
    }

    private fun cacheToSinglePost(postCache: PostCache?): Post {
        return Post(
            body = postCache?.body.orEmpty(),
            title = postCache?.title.orEmpty(),
            userId = postCache?.userId ?: 0
        )
    }

    fun responseToUserListCache(userResponseList: List<UserResponse>): List<UserCache> {
        val userList = ArrayList<UserCache>()
        userResponseList.map {
            userList.add(responseToSingleUserCache(it))
        }
        return userList
    }

    private fun responseToSingleUserCache(userResponse: UserResponse?): UserCache {
        return UserCache(
            idLocal = null,
            idUser = userResponse?.idUser ?: 0,
            name = userResponse?.name.orEmpty(),
            username = userResponse?.username.orEmpty(),
            email = userResponse?.email.orEmpty(),
            phone = userResponse?.phone.orEmpty(),
            website = userResponse?.website.orEmpty(),
            addressCache = responseToSingleAddressCache(userResponse?.address),
            companyCache = responseToSingleCompanyCache(userResponse?.company),
        )
    }


    private fun responseToSingleCompanyCache(companyResponse: CompanyResponse?): CompanyCache {
        return CompanyCache(
            catchPhrase = companyResponse?.catchPhrase.orEmpty(),
            nameCompany = companyResponse?.nameCompany.orEmpty(),
            bs = companyResponse?.bs.orEmpty()
        )
    }

    private fun responseToSingleAddressCache(addressResponse: AddressResponse?): AddressCache {
        return AddressCache(
            street = addressResponse?.street.orEmpty(),
            suite = addressResponse?.suite.orEmpty(),
            zipcode = addressResponse?.zipcode.orEmpty(),
            geoCache = responseToSingleGeoCache(addressResponse?.geo),
        )
    }

    private fun responseToSingleGeoCache(geoResponse: GeoResponse?): GeoCache {
        return GeoCache(
            lat = geoResponse?.lat.orEmpty(),
            lng = geoResponse?.lng.orEmpty()
        )
    }

    fun responseToCommentListCache(commentResponseList: List<CommentResponse>): List<CommentCache> {
        val userList = ArrayList<CommentCache>()
        commentResponseList.map {
            userList.add(responseToSingleCommentCache(it))
        }
        return userList
    }

    private fun responseToSingleCommentCache(commentResponse: CommentResponse): CommentCache {
        return CommentCache(
            id = 0,
            postId = commentResponse.postId ?: 0,
            name = commentResponse.name.orEmpty(),
            email = commentResponse.email.orEmpty(),
            body = commentResponse.body.orEmpty()
        )
    }

    fun cacheToSingleUser(user: UserCache?): User {
        return User(
            idUser = user?.idUser ?: 0,
            name = user?.name.orEmpty(),
            username = user?.username.orEmpty(),
            email = user?.email.orEmpty(),
            phone = user?.phone.orEmpty(),
            website = user?.website.orEmpty(),
            address = cacheToSingleAddress(user?.addressCache),
            company = cacheToSingleCompany(user?.companyCache),
        )
    }

    private fun cacheToSingleAddress(addressCache: AddressCache?): Address {
        return Address(
            street = addressCache?.street.orEmpty(),
            suite = addressCache?.suite.orEmpty(),
            zipcode = addressCache?.zipcode.orEmpty(),
            geo = cacheToSingleGeo(addressCache?.geoCache),
        )
    }

    private fun cacheToSingleGeo(geoCache: GeoCache?): Geo {
        return Geo(
            lat = geoCache?.lat.orEmpty(),
            lng = geoCache?.lng.orEmpty()
        )
    }

    private fun cacheToSingleCompany(companyCache: CompanyCache?): Company {
        return Company(
            nameCompany = companyCache?.nameCompany.orEmpty(),
            catchPhrase = companyCache?.catchPhrase.orEmpty(),
            bs = companyCache?.bs.orEmpty(),
        )
    }

    fun cacheToCommentList(commentCacheList: List<CommentCache>?): List<Comment> {
        val commentList = ArrayList<Comment>()
        commentCacheList?.map {
            commentList.add(cacheToSingleComment(it))
        }
        return commentList
    }

    private fun cacheToSingleComment(commentCache: CommentCache?): Comment {
        return Comment(
            postId = commentCache?.postId ?: 0,
            name = commentCache?.name.orEmpty(),
            email = commentCache?.email.orEmpty(),
            body = commentCache?.body.orEmpty(),
        )
    }
}