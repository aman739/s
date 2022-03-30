package com.example.rickandmorty_.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty_.databinding.ItemLocationBinding
import com.example.rickandmorty_.models.RickAndMortyLocation

class LocationAdapter : ListAdapter<RickAndMortyLocation, LocationAdapter.LocationViewHolder>(
    LocationComparator
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LocationViewHolder =
        LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RickAndMortyLocation) {
            binding.tvLocationsTwo.text = data.name
            binding.tvLocationsThree.text = data.type
            binding.tvLocationsFour.text = data.dimension
        }
    }

    object LocationComparator : DiffUtil.ItemCallback<RickAndMortyLocation>() {
        override fun areItemsTheSame(
            oldItem: RickAndMortyLocation,
            newItem: RickAndMortyLocation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RickAndMortyLocation,
            newItem: RickAndMortyLocation
        ): Boolean {
            return oldItem == newItem
        }
    }
}