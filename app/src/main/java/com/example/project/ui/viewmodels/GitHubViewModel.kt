package com.example.project.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.project.data.model.GitHubUser
import com.example.project.data.model.UserProfile
import com.example.project.network.GitHubApiService
import com.example.project.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class GitHubViewModel : ViewModel() {
private val apiService = RetrofitClient.retrofit.create(GitHubApiService::class.java)
    private val _users = MutableStateFlow<List<GitHubUser>>(emptyList())
    val users: StateFlow<List<GitHubUser>> = _users
    suspend fun searchUsers(user: String): Result<Unit> {
        println("viewModel before try")
        println(user)

        try {
            println("view model after try")
            val usersResult = apiService.searchUsers(user= user)
            println("user Results")
            _users.value = usersResult.items
            println(_users.value)
            return Result.success(Unit)
        } catch (e: Exception) {
            println("view model after catch")
            println(e.message)
            return Result.failure(e)
        }
    }

        suspend fun getUser(username: String): UserProfile {
            var user: UserProfile = apiService.getUser(username)
            return user
        }


}