package com.yeonkyu.domain.usecase.auth

import com.yeonkyu.domain.model.User
import com.yeonkyu.domain.repository.LoginRepository
import javax.inject.Inject

class AuthorizeUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    fun execute(
        user: User,
        onSuccess: () -> Unit,
        onError: (errorCode: String, errorMessage: String) -> Unit
    ) {
        repository.authorize(user, onSuccess, onError)
    }
}