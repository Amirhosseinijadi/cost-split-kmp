package com.costsplit.core.network.remote

import com.costsplit.core.common.result.ApiError
import com.costsplit.core.common.result.AppResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

open class BaseRepository {
    protected suspend fun <T> apiCall(block: suspend () -> T): AppResult<T> = try {
        AppResult.Success(block())
    } catch (error: ClientRequestException) {
        if (error.response.status.value == 401) AppResult.Failure(ApiError.Unauthorized)
        else AppResult.Failure(ApiError.Server(error.response.status.value))
    } catch (error: ServerResponseException) {
        AppResult.Failure(ApiError.Server(error.response.status.value))
    } catch (error: Throwable) {
        AppResult.Failure(ApiError.Network)
    }
}
