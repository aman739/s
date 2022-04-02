package com.example.rickandmorty_.ui.fragments.locations

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.databinding.FragmentLocationBinding
import com.example.rickandmorty_.ui.adapters.LocationAdapter
import com.example.rickandmorty_.common.extensions.submitDataPaging
import com.example.rickandmorty_.ulits.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

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
        subscribeToLocationsLocale()
    }
    private fun setupAdapter() = with(binding.recyclerviewLocations) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = locationAdapter

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, {
                if (isOnline()) {
                    viewModel.fetchLocations()
                } else {
                    null
                }
            }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToLocation() {
        viewModel.locationState.observe(viewLifecycleOwner) {
            locationAdapter.submitDataPaging(it.results)
            Log.e("bankai", "subscribeToEpisodes: ${it.results}")
        }
    }

    private fun subscribeToLocationsLocale() {
        viewModel.locationLocateState.observe(viewLifecycleOwner) {
            locationAdapter.submitDataPaging(it)

        }
    }

    override fun setupRequests() {
        if (viewModel.locationState.value == null && isOnline()) viewModel.fetchLocations()
        else viewModel.getEpisodes()
    }

    fun isOnline(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}