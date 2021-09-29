package com.example.data.network.api.response

import com.google.gson.annotations.SerializedName

data class DtoCompany(
    @SerializedName("name")
    val nameCompany: String?,
    @SerializedName("catchPhrase")
    val catchPhrase: String?,
    @SerializedName("bs")
    val bs: String?,
) {
    override fun toString(): String {
        return "Company(nameCompany='$nameCompany', catchPhrase='$catchPhrase', bs='$bs')"
    }
}