package com.example.githubapisample.data.remote

/**
 * 検索結果クラスのitemsの内容
 * 一部抜粋
 * 全データは以下
 * https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
 */
data class RepositoryData(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
)
