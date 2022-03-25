package com.example.rickandmorty_.ui.fragments.characters.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.databinding.FragmentDetailBinding
import com.example.rickandmorty_.ui.fragments.characters.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>(
    R.layout.fragment_detail
) {

    override val binding by viewBinding(FragmentDetailBinding::bind)
    override val viewModel: CharactersViewModel by activityViewModels()

}