package com.example.domain.model

import com.example.domain.model.Address
import com.example.domain.model.Company

data class User(
    var idUser: Int,
    var name: String,
    var username: String,
    var email: String,
    var phone: String,
    var website: String,
    var address: Address,
    var company: Company,
)
