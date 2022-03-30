package com.example.rickandmorty_.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty_.databinding.ItemEpisodesBinding
import com.example.rickandmorty_.models.RickAndMortyEpisodes

class EpisodesAdapter :
    ListAdapter<RickAndMortyEpisodes, EpisodesAdapter.EpisodesViewHolder>(
        EpisodesComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            ItemEpisodesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class EpisodesViewHolder(private val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RickAndMortyEpisodes) {
            binding.tvEpisodesOne.text = data.name
            binding.tvEpisodesTwo.text = data.episode
        }
    }

    object EpisodesComparator : DiffUtil.ItemCallback<RickAndMortyEpisodes>() {
        override fun areItemsTheSame(
            oldItem: RickAndMortyEpisodes,
            newItem: RickAndMortyEpisodes
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RickAndMortyEpisodes,
            newItem: RickAndMortyEpisodes
        ): Boolean {
            return oldItem == newItem
        }
    }
}