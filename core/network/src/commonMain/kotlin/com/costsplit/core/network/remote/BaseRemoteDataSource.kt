package com.costsplit.core.network.remote

import com.costsplit.core.common.coroutine.AppDispatchers
import com.costsplit.core.common.result.ApiError
import com.costsplit.core.common.result.AppResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withContext

open class BaseRemoteDataSource(
    private val dispatchers: AppDispatchers,
) {
    protected suspend fun <T> safeApiCall(block: suspend () -> T): AppResult<T> =
        withContext(dispatchers.io) {
            try {
                AppResult.Success(block())
            } catch (error: ClientRequestException) {
                AppResult.Failure(error.response.status.value.toApiError())
            } catch (error: ServerResponseException) {
                AppResult.Failure(ApiError.Server(error.response.status.value))
            } catch (error: CancellationException) {
                throw error
            } catch (error: Throwable) {
                AppResult.Failure(ApiError.Network)
            }
        }

    private fun Int.toApiError(): ApiError = when (this) {
        401 -> ApiError.Unauthorized
        else -> ApiError.Server(this)
    }
}
