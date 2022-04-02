package com.example.rickandmorty_.ui.fragments.episodes

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.common.extensions.submitDataPaging
import com.example.rickandmorty_.databinding.FragmentEpisodesBinding
import com.example.rickandmorty_.ui.adapters.EpisodesAdapter
import com.example.rickandmorty_.ulits.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding,EpisodesViewModel>(
    R.layout.fragment_episodes
) {
    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()
    private val episodesAdapter = EpisodesAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    override fun setupObserves() {
        subscribeToEpisodes()
        subscribeToEpisodesLocale()
    }
    private fun setupAdapter() = with(binding.recyclerviewEpisodes) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = episodesAdapter

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, {
                if (isOnline()) {
                    viewModel.fetchEpisodes()
                } else {
                    null
                }
            }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToEpisodes() {
        viewModel.episodesState.observe(viewLifecycleOwner) {
           episodesAdapter.submitDataPaging(it.results)
            Log.e("bankai", "subscribeToEpisodes: ${it.results}")
        }
    }

    private fun subscribeToEpisodesLocale() {
        viewModel.episodesLocateState.observe(viewLifecycleOwner) {
            episodesAdapter.submitDataPaging(it)

        }
    }

    override fun setupRequests() {
        if (viewModel.episodesState.value == null && isOnline()) viewModel.fetchEpisodes()
        else viewModel.getEpisodes()
    }

    fun isOnline(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
