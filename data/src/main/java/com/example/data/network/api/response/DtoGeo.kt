package com.example.data.network.api.response

import com.google.gson.annotations.SerializedName

data class DtoGeo(
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lng")
    var lng: Double?
)