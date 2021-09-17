package com.example.newhealios.data.cloud.api.response

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    val street: String?,
    val suite: String?,
    val zipcode: String?,
    @SerializedName("geo")
    var geo: GeoResponse?,
) {
    override fun toString(): String {
        return "Address(street='$street', suite='$suite', zipcode='$zipcode', geo=$geo)"
    }
}

