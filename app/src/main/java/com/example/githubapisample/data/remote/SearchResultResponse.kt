package com.example.githubapisample.data.remote

/**
 * 検索結果のレスポンスのクラス
 */
data class SearchResultResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepositoryData>,
)

