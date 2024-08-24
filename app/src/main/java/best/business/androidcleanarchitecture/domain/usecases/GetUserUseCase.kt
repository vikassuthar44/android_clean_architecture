package best.business.androidcleanarchitecture.domain.usecases

import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import best.business.androidcleanarchitecture.domain.repository.GithubRepository
import best.business.androidcleanarchitecture.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {
    suspend operator fun invoke(): Flow<ApiResponse<List<UserResponse>>> {
        return githubRepository.getGithubUsers()
    }
}