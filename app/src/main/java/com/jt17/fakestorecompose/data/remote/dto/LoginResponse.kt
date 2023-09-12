package com.jt17.fakestorecompose.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val user: User,
) {

    @Serializable
    data class User(val id: Int, val username: String, val email: String)

}
