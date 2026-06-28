package com.costsplit.feature.users.domain.usecase

import com.costsplit.feature.users.domain.repository.UserRepository

class GetUsersUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke() = repository.listUsers()
}
