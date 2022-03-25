package com.example.rickandmorty_.ui.fragments.characters

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.databinding.FragmentCharacterBinding
import com.example.rickandmorty_.databinding.ProgressBarLoaderStateBinding
import com.example.rickandmorty_.ui.adapters.CharacterAdapter
import com.example.rickandmorty_.ui.adapters.PagingStateADapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharactersViewModel>(
    R.layout.fragment_character
) {
    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerviewCharacter.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewCharacter.adapter = characterAdapter.withLoadStateHeaderAndFooter(
            header = PagingStateADapter{characterAdapter.retry()},
            footer = PagingStateADapter{characterAdapter.retry()},
        )
    }

    override fun setupObserves() {
        subscribeToCharacters()
    }

    private fun subscribeToCharacters() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }
}




