package com.example.data.network.api.response

import com.google.gson.annotations.SerializedName

data class DtoAddress(
    @SerializedName("street")
    val street: String?,
    @SerializedName("suite")
    val suite: String?,
    @SerializedName("zipcode")
    val zipcode: String?,
    @SerializedName("geo")
    var geo: DtoGeo?,
) {
    override fun toString(): String {
        return "Address(street='$street', suite='$suite', zipcode='$zipcode', geo=$geo)"
    }
}

