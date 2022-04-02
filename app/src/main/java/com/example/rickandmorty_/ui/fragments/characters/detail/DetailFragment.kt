package com.example.rickandmorty_.ui.fragments.characters.detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.common.extensions.setImage
import com.example.rickandmorty_.common.resource.Resource
import com.example.rickandmorty_.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>(
    R.layout.fragment_detail
) {

    override val binding by viewBinding(FragmentDetailBinding::bind)
    override val viewModel: DetailsViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    override fun setupObserves() {
        subscribeToCharacters()
    }
    private fun subscribeToCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchSingleCharacter(args.id).collect {
                when (it) {
                    is Resource.Loading -> {
                        Log.e("bankai", "Loading")
                    }
                    is Resource.Error -> {
                        Log.e("bankai", "error,${it.message.toString()}")
                    }
                    is Resource.Success -> {
                   binding.tvName.text = it.data?.name
                        it.data?.let { it1 -> binding.ivImage.setImage(it1.image) }

                        }
                    }
                }
            }
        }
    }


