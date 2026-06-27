package com.costsplit.core.common.result

import com.costsplit.core.common.utils.Constant

sealed interface ApiError {
    data object Network : ApiError
    data object Unauthorized : ApiError
    data class Server(val code: Int) : ApiError
    data class Unknown(val cause: Throwable? = null) : ApiError
}

fun ApiError.message(): String = when (this) {
    ApiError.Network -> Constant.NETWORK_ERROR_MESSAGE
    ApiError.Unauthorized -> Constant.UN_AUTHORIZED_MESSAGE
    is ApiError.Server -> "${Constant.SERVER_ERROR_MESSAGE} ($code)"
    is ApiError.Unknown -> cause?.message ?: Constant.UNKNOWN_ERROR_MESSAGE
}

