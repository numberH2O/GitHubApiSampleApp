package com.example.githubapisample.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.githubapisample.R

class RepositoryNameAdapter(
    context: Context,
    private val resource: Int,
    private val items: List<String>,
) : ArrayAdapter<String>(
    context,
    resource,
    items,
) {
    private val inflater: LayoutInflater = context.getSystemService(
        Context.LAYOUT_INFLATER_SERVICE
    ) as LayoutInflater

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view = convertView ?: inflater.inflate(resource, null)

        val name = items[position]

        val nameView = view.findViewById<TextView>(R.id.repository_name)
        nameView.text = name
        return view
    }
}