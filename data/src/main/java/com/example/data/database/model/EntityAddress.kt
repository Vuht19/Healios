package com.example.data.database.model

import androidx.room.Embedded


data class EntityAddress(
    var street: String,
    var suite: String,
    var zipcode: String,
    @Embedded
    var geoCache: EntityGeo,
) {
    override fun toString(): String {
        return "Address(street='$street', suite='$suite', zipcode='$zipcode', geo=$geoCache)"
    }
}

