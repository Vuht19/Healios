package com.example.newhealios.data.network.api.response

import com.google.gson.annotations.SerializedName

data class DtoGeo(
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lng")
    var lng: String?
)