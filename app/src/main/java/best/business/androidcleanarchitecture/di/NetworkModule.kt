package best.business.androidcleanarchitecture.di

import best.business.androidcleanarchitecture.data.network.ApiHelper
import best.business.androidcleanarchitecture.data.network.ApiHelperImpl
import best.business.androidcleanarchitecture.data.network.ApiService
import best.business.androidcleanarchitecture.data.repository.NetworkRepositoryImpl
import best.business.androidcleanarchitecture.domain.repository.GithubRepository
import best.business.androidcleanarchitecture.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper {
        return apiHelperImpl
    }

    @Provides
    @Singleton
    fun provideGithubRepository(networkRepositoryImpl: NetworkRepositoryImpl): GithubRepository {
        return networkRepositoryImpl
    }
}