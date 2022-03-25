package com.example.rickandmorty_.ui.fragments.episodes

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.databinding.FragmentEpisodesBinding
import com.example.rickandmorty_.ui.adapters.AdapterEpisodes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
    R.layout.fragment_episodes
) {

    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()
    private val adapterEpisodes = AdapterEpisodes()


    override fun initialize() {
        binding.recyclerviewEpisodes.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewEpisodes.adapter = adapterEpisodes
    }

    override fun setupObserves() {
        subscribeToEpisodes()

    }

    private fun subscribeToEpisodes() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                adapterEpisodes.submitData(it)
            }
        }
    }
}

