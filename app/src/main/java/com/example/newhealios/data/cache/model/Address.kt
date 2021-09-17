package com.example.newhealios.data.cache.model

import androidx.room.Embedded


data class Address(
    var street: String,
    var suite: String,
    var zipcode: String,
    @Embedded
    var geo: Geo,
) {
    override fun toString(): String {
        return "Address(street='$street', suite='$suite', zipcode='$zipcode', geo=$geo)"
    }
}

