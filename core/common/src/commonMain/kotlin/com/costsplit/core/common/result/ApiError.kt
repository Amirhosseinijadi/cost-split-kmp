package com.costsplit.core.common.result

sealed interface ApiError {
    data object Network : ApiError
    data object Unauthorized : ApiError
    data class Server(val code: Int) : ApiError
    data class Unknown(val cause: Throwable? = null) : ApiError
}
