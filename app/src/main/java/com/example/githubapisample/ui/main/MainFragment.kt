package com.example.githubapisample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.githubapisample.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

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
        view.findViewById<ListView>(R.id.listview).adapter = repositoryNameAdapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}