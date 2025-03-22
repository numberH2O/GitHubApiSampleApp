package com.example.githubapisample.service

import com.example.githubapisample.data.remote.SearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    @GET("search/repositories")
    suspend fun getSearchRepository(
        @Query("q") q: String,
    ): SearchResultResponse
}
