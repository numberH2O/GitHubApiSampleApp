package com.example.githubapisample.ui.main

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private lateinit var listView: ListView

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

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        listView = view.findViewById(R.id.listview)
        listView.adapter = repositoryNameAdapter

        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            // 検索結果をリセット
            listView.adapter = repositoryNameAdapter

            // 検索したので検索文字列を表示
            view.findViewById<ConstraintLayout>(R.id.searched_word_area).visibility = View.VISIBLE

            val searchWord = view.findViewById<EditText>(R.id.search_word).text.toString()
            // 検索文字列を保存
            view.findViewById<TextView>(R.id.searched_word).text = searchWord
            // 検索処理
            viewModel.search(searchWord)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        startObserve()
    }

    private fun startObserve() {
        val observer = Observer<SearchResult> {
            listView.adapter = RepositoryNameAdapter(
                context = context!!,
                resource = R.layout.list_item,
                items = it.nameList,
            )
        }

        viewModel.repositoryNames.observe(
            this,
            observer,
        )
    }
}
