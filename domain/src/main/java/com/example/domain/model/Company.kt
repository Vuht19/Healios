package com.example.domain.model

data class Company(
    var nameCompany: String,
    var catchPhrase: String,
    var bs: String,
) {
    override fun toString(): String {
        return "Company(nameCompany='$nameCompany', catchPhrase='$catchPhrase', bs='$bs')"
    }
}