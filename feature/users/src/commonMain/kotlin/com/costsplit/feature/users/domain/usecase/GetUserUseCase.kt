package com.costsplit.feature.users.domain.usecase

import com.costsplit.feature.users.domain.repository.UserRepository

class GetUserUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(userId: String) = repository.getUser(userId)
}
