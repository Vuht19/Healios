package com.example.domain.model

import android.location.Location

data class Address(
    var street: String,
    var suite: String,
    var zipcode: String,
    var location: Location,
) {
    override fun toString(): String {
        return "Address(street='$street', suite='$suite', zipcode='$zipcode', location=$location)"
    }
}

