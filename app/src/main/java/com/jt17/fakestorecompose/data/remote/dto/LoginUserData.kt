package com.jt17.fakestorecompose.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserData(
    val username: String,
    val password: String
)
