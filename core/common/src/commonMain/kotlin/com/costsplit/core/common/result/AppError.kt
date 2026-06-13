package com.costsplit.core.common.result

sealed interface AppError {
    data object Network : AppError
    data object Unauthorized : AppError
    data class Server(val code: Int) : AppError
    data class Unknown(val cause: Throwable? = null) : AppError
}

