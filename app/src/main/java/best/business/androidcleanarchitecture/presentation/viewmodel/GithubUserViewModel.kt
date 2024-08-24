package best.business.androidcleanarchitecture.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import best.business.androidcleanarchitecture.domain.usecases.GetUserUseCase
import best.business.androidcleanarchitecture.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _users = MutableStateFlow<ApiResponse<List<UserResponse>>>(ApiResponse.Loading)
    val users: StateFlow<ApiResponse<List<UserResponse>>> = _users

    init {
        fetchGithubUsers()
    }

    private fun fetchGithubUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase().collect { response ->
                when (response) {
                    is ApiResponse.Failure -> {
                        _users.value = ApiResponse.Failure("Something went wrong")
                    }

                    ApiResponse.Loading -> {
                        _users.value = ApiResponse.Loading
                    }

                    is ApiResponse.Success -> {
                        _users.value = ApiResponse.Success(response.data)
                    }
                }
            }
        }
    }
}