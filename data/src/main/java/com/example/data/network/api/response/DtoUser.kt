package com.example.data.network.api.response

import com.example.data.network.api.response.DtoAddress
import com.example.data.network.api.response.DtoCompany
import com.google.gson.annotations.SerializedName

data class DtoUser(
    var idLocal: Int?,
    @SerializedName("id")
    var idUser: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("website")
    var website: String?,
    @SerializedName("address")
    var address: DtoAddress?,
    @SerializedName("company")
    var company: DtoCompany?,
)
