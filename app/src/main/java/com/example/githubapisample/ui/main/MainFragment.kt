package com.example.githubapisample.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.githubapisample.R
import com.example.githubapisample.data.local.SearchResult

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var searchButton: Button
    private lateinit var editEditText: EditText
    private lateinit var listView: ListView

    private lateinit var rootView: View

    private lateinit var repositoryNameAdapter: RepositoryNameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        repositoryNameAdapter = RepositoryNameAdapter(
            context = context!!,
            resource = R.layout.list_item,
            items = emptyList(),
        )

        rootView = inflater.inflate(R.layout.main_fragment, container, false)

        listView = rootView.findViewById(R.id.listview)
        listView.adapter = repositoryNameAdapter

        editEditText = rootView.findViewById(R.id.searched_word)

        searchButton = rootView.findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            trySearch()
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        startObserve()
    }

    private fun trySearch() {
        // 検索中なら重ねて検索は行わない
        if (viewModel.isSearching.value == true) {
            return
        }

        // キーボードをしまう
        (context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(rootView.windowToken, 0)

        val searchWord = editEditText.text.toString()
        // 検索処理
        viewModel.search(searchWord)
        // 検索文字列を保存
        rootView.findViewById<TextView>(R.id.searched_word).text = searchWord

        // 検索結果をリセット
        listView.adapter = repositoryNameAdapter

        // 検索したので検索文字列を表示
        rootView.findViewById<ConstraintLayout>(R.id.searched_word_area).apply {
            visibility = View.VISIBLE
        }
    }

    private fun startObserve() {
        val resultObserver = Observer<SearchResult> {
            listView.adapter = RepositoryNameAdapter(
                context = context!!,
                resource = R.layout.list_item,
                items = it.nameList,
            )
        }

        viewModel.repositoryNames.observe(
            this,
            resultObserver,
        )

        val searchingObserver = Observer<Boolean> {
            // 検索中はクリックできなくする
            searchButton.isClickable = !it
        }

        viewModel.isSearching.observe(
            this,
            searchingObserver,
        )
    }
}
