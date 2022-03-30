package com.example.rickandmorty_.ui.fragments.characters

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.databinding.FragmentCharacterBinding
import com.example.rickandmorty_.ui.adapters.CharacterAdapter
import com.example.rickandmorty_.common.extensions.submitDataPaging
import com.example.rickandmorty_.ulits.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharactersViewModel>(
    R.layout.fragment_character
) {
    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(this :: onItemClick)

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() = with(binding.recyclerviewCharacter) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = characterAdapter

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, { viewModel.fetchCharacters() }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    override fun setupObserves() {
        subscribeToCharacters()
    }

    private fun subscribeToCharacters() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            characterAdapter.submitDataPaging(it)
        }
    }
    private fun onItemClick(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(id)
        )
    }
}