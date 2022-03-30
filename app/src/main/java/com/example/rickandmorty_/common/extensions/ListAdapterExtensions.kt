package com.example.rickandmorty_.common.extensions

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

fun <T, D : RecyclerView.ViewHolder> ListAdapter<T, D>.submitDataPaging(data: List<T>) {
    val dataList = ArrayList<T>(currentList)
    dataList.addAll(data)
    submitList(dataList)
}