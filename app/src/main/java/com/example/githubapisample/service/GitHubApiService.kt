package com.example.githubapisample.service

import com.example.githubapisample.data.remote.SearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * githubのAPIをretrofitで利用するためのinterface
 */
interface GitHubApiService {

    /**
     * リポジトリの検索
     */
    @GET("search/repositories")
    suspend fun getSearchRepository(
        @Query("q") query: String,
    ): SearchResultResponse
}
