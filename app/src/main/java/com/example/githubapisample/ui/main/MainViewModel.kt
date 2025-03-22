package com.example.githubapisample.ui.main

import androidx.lifecycle.*
import com.example.githubapisample.data.local.SearchResult
import com.example.githubapisample.repository.GitHubRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val gitHubRepository = GitHubRepository()

    val repositoryNames: MutableLiveData<SearchResult> by lazy {
        MutableLiveData<SearchResult>()
    }

    fun search() {
        viewModelScope.launch {
            val result = runCatching {
                gitHubRepository.getSearchResult("Android")
            }

            result.onSuccess {
                repositoryNames.value = it
            }.onFailure {
                // エラー処理
            }
        }
    }
}
