package com.example.newhealios.domain.model

import com.example.newhealios.data.cloud.api.response.GeoResponse

data class Address(
    var street: String,
    var suite: String,
    var zipcode: String,
    var geo: Geo,
) {
    override fun toString(): String {
        return "Address(street='$street', suite='$suite', zipcode='$zipcode', geo=$geo)"
    }
}

