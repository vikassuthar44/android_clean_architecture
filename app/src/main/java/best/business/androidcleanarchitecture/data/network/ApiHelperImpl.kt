package best.business.androidcleanarchitecture.data.network

import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import best.business.androidcleanarchitecture.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService,
) : ApiHelper {
    private suspend fun <T> safeApiCall(
        apiCall: suspend () -> retrofit2.Response<T>,
    ): Flow<ApiResponse<T>> = flow {
        emit(ApiResponse.Loading)
        val response = apiCall()
        if (response.isSuccessful) {
            when (response.code()) {
                200 -> {
                    response.body()?.let {
                        emit(ApiResponse.Success(it))
                    } ?: run {
                        emit(ApiResponse.Failure("Something Went Wrong...."))
                    }
                }

                else -> {
                    response.body()?.let {
                        emit(ApiResponse.Success(it))
                    } ?: run {
                        emit(ApiResponse.Failure("Something Went Wrong...."))
                    }
                }
            }
        } else {
            response.body()?.let {
                emit(ApiResponse.Failure(it.toString()))
            }
        }
    }

    override suspend fun getGithubUsers(): Flow<ApiResponse<List<UserResponse>>> {
        return safeApiCall(
            apiCall = {
                apiService.getGithubUsers()
            }
        )
    }
}