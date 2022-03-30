package com.example.rickandmorty_.ui.fragments.locations

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.databinding.FragmentLocationBinding
import com.example.rickandmorty_.ui.adapters.LocationAdapter
import com.example.rickandmorty_.common.extensions.submitDataPaging
import com.example.rickandmorty_.ulits.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding, LocationViewModel>(
    R.layout.fragment_location
) {
    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter()

    override fun setupViews() {
        setupAdapter()
    }
    override fun setupObserves() {
        subscribeToLocation()
    }

    private fun setupAdapter() = with(binding.recyclerviewLocations) {
        adapter = locationAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, { viewModel.fetchLocation() }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToLocation() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.locationState.observe(viewLifecycleOwner) {
                locationAdapter.submitDataPaging(it)
            }
        }
    }
}
