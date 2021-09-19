package com.example.newhealios.data.cache.model

import androidx.room.Embedded


data class AddressCache(
    var street: String,
    var suite: String,
    var zipcode: String,
    @Embedded
    var geoCache: GeoCache,
) {
    override fun toString(): String {
        return "Address(street='$street', suite='$suite', zipcode='$zipcode', geo=$geoCache)"
    }
}

