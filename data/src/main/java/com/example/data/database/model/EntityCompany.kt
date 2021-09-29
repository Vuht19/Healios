package com.example.data.database.model

data class EntityCompany(
    var nameCompany: String,
    var catchPhrase: String,
    var bs: String,
) {
    override fun toString(): String {
        return "Company(nameCompany='$nameCompany', catchPhrase='$catchPhrase', bs='$bs')"
    }
}