package com.example.githubapisample.repository

import com.example.githubapisample.service.GitHubApiService
import com.example.githubapisample.data.local.SearchResult
import com.example.githubapisample.data.converter.toSearchResult
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GitHubRepository {
    private val baseUrl = "https://api.github.com"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(moshi)
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GitHubApiService by lazy {
        retrofit.create(GitHubApiService::class.java)
    }

    /**
     * @param q クエリ文字列
     */
    suspend fun getSearchResult(q: String): SearchResult {
        return retrofitService.getSearchRepository(
            q = q,
        ).toSearchResult()
    }
}
