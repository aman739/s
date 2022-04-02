package com.example.rickandmorty_.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty_.common.extensions.setImage
import com.example.rickandmorty_.databinding.ItemCharacterBinding
import com.example.rickandmorty_.models.RickAndMortyCharacter

class CharacterAdapter(
    private val onItemClick: (id: Int) -> Unit
) :
    ListAdapter<RickAndMortyCharacter, CharacterAdapter.CharacterViewHolder>(
        CharacterComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RickAndMortyCharacter) {
            binding.ivCharacter.setImage(data.image)
            binding.tvName.text = data.name
        }
        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { m -> onItemClick(m.id) }
            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<RickAndMortyCharacter>() {
        override fun areItemsTheSame(
            oldItem: RickAndMortyCharacter,
            newItem: RickAndMortyCharacter
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RickAndMortyCharacter,
            newItem: RickAndMortyCharacter
        ): Boolean {
            return oldItem == newItem
        }
    }
}
