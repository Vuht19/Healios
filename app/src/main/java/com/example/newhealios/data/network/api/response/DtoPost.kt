package com.example.newhealios.data.network.api.response

import com.google.gson.annotations.SerializedName

data class DtoPost(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
)