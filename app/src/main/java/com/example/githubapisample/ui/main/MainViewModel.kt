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

    /**
     * 検索中を示すフラグ
     */
    val isSearching: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    /**
     * エラー時に表示する文字
     */
    val isError: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>(null)
    }

    fun search(searchWord: String) {
        viewModelScope.launch {
            //　検索開始
            isSearching.value = true

            // エラー状態リセット
            isError.value = null

            val result = runCatching {
                gitHubRepository.getSearchResult(query = searchWord)
            }

            result.onSuccess {
                repositoryNames.value = it
            }.onFailure {
                isError.value = "エラーが発生しました"
            }

            //　検索修了
            isSearching.value = false
        }
    }
}
