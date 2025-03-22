package com.example.githubapisample.data.remote

data class SearchResultResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepositoryData>,
)

