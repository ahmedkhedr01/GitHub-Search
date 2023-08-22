package com.example.project.data.model

data class GitHubResponse (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<GitHubUser>
)