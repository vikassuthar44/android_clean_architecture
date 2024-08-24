package best.business.androidcleanarchitecture.data.network

import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getGithubUsers() : Response<List<UserResponse>>
}