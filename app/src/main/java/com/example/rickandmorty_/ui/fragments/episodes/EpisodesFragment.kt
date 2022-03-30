package com.example.rickandmorty_.ui.fragments.episodes

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.databinding.FragmentEpisodesBinding
import com.example.rickandmorty_.ui.adapters.EpisodesAdapter
import com.example.rickandmorty_.common.extensions.submitDataPaging
import com.example.rickandmorty_.ulits.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
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
    }

    private fun setupAdapter() = with(binding.recyclerviewEpisodes) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = episodesAdapter
        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, { viewModel.fetchEpisodes() }) {
            override fun isLoading() = viewModel.isLoading
        })
    }
    private fun subscribeToEpisodes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodesState.observe(viewLifecycleOwner) {
                episodesAdapter.submitDataPaging(it)
            }
        }
    }
}

