package com.example.newhealios.data.cloud.api.response

import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @SerializedName("name")
    val nameCompany: String?,
    val catchPhrase: String?,
    val bs: String?,
) {
    override fun toString(): String {
        return "Company(nameCompany='$nameCompany', catchPhrase='$catchPhrase', bs='$bs')"
    }
}