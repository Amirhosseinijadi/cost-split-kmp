package com.costsplit.feature.users.domain.usecase

import com.costsplit.feature.users.domain.model.NewUser
import com.costsplit.feature.users.domain.repository.UserRepository

class CreateUserUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(user: NewUser) = repository.createUser(user)
}
