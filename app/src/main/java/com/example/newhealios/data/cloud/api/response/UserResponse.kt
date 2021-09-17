package com.example.newhealios.data.cloud.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    var idLocal: Int?,
    @SerializedName("id")
    var idUser: Int?,
    var name: String?,
    var username: String?,
    var email: String?,
    var phone: String?,
    var website: String?,
    @SerializedName("address")
    var address: AddressResponse?,
    @SerializedName("company")
    var company: CompanyResponse?,
)
