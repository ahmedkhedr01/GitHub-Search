package com.example.project.network

import com.example.project.data.model.GitHubResponse
import com.example.project.data.model.GitHubUser
import com.example.project.data.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") user: String, @Query("page") page: Int = 1): GitHubResponse

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserProfile
}