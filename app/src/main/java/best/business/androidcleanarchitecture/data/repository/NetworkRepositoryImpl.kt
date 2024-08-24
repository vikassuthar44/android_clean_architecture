package best.business.androidcleanarchitecture.data.repository

import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import best.business.androidcleanarchitecture.data.network.ApiHelper
import best.business.androidcleanarchitecture.domain.repository.GithubRepository
import best.business.androidcleanarchitecture.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
) : GithubRepository {
    override suspend fun getGithubUsers(): Flow<ApiResponse<List<UserResponse>>> {
        return apiHelper.getGithubUsers()
    }
}