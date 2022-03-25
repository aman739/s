package com.example.rickandmorty_.ui.fragments.locations

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.databinding.FragmentLocationBinding
import com.example.rickandmorty_.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding, LocationViewModel>(
    R.layout.fragment_location
) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val adapterLocation = LocationAdapter()


    override fun initialize() {
        binding.recyclerviewLocations.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewLocations.adapter = adapterLocation
    }

    override fun setupObserves() {
        subscribeToLocation()
    }

    private fun subscribeToLocation() {
        lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                adapterLocation.submitData(it)

            }
        }
    }
}