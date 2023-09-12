package com.jt17.fakestorecompose.data.mapper

import com.jt17.fakestorecompose.data.remote.dto.LoginResponse
import com.jt17.fakestorecompose.domain.model.Login

fun LoginResponse.toLogin(): Login = Login(
    id = user.id,
    token = token,
    username = user.username,
    email = user.email,
)