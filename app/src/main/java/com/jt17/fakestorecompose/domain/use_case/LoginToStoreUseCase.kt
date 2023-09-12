package com.jt17.fakestorecompose.domain.use_case

import com.jt17.fakestorecompose.domain.model.Login
import com.jt17.fakestorecompose.domain.model.Resource
import com.jt17.fakestorecompose.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow

class LoginToStoreUseCase(private val repository: FakeStoreRepository) {

    suspend operator fun invoke(username: String, password: String): Flow<Resource<Login>> =
        repository.loginToStore(username = username, password = password)

}