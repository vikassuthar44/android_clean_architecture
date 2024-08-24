package best.business.androidcleanarchitecture.domain.repository

import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import best.business.androidcleanarchitecture.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getGithubUsers(): Flow<ApiResponse<List<UserResponse>>>
}