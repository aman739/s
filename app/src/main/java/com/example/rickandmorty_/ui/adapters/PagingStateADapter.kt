package com.example.rickandmorty_.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty_.databinding.ProgressBarLoaderStateBinding

class PagingStateADapter (private val retery: ()-> Unit): LoadStateAdapter<PagingStateADapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            ProgressBarLoaderStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

   inner class LoadStateViewHolder(private val binding: ProgressBarLoaderStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

       init {
           retery.invoke()
       }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }

}