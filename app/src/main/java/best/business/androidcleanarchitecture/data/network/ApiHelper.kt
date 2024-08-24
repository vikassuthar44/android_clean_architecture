package best.business.androidcleanarchitecture.data.network

import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import best.business.androidcleanarchitecture.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getGithubUsers(): Flow<ApiResponse<List<UserResponse>>>
}