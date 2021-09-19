package com.example.newhealios.domain.model

import java.io.Serializable

data class Post(
    var body: String,
    var title: String,
    var userId: Int
) : Serializable