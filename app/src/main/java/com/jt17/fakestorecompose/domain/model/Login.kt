package com.jt17.fakestorecompose.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Login(
    val id: Int? = null,
    val token: String? = null,
    val username: String = "",
    val email: String = "",
)