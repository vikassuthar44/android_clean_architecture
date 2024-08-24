package best.business.androidcleanarchitecture.util

sealed class ApiResponse<out T> {
    data object Loading : ApiResponse<Nothing>()
    data class Success<T>(
        val data: T,
    ) : ApiResponse<T>()

    data class Failure(val exception: String) : ApiResponse<Nothing>()
}