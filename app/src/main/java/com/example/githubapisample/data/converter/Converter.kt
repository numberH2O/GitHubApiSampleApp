package com.example.githubapisample.data.converter

import com.example.githubapisample.data.local.SearchResult
import com.example.githubapisample.data.remote.SearchResultResponse

fun SearchResultResponse.toSearchResult(): SearchResult {
    return SearchResult(
        nameList = items.map {
            it.name
        }
    )
}

