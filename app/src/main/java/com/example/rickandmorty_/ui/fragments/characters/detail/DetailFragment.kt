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
    private val args by navArgs<DetailFragmentArgs>()

    override fun setupViews() {
        getData()
    }

    private fun getData() {
//        viewModel.fetchCharacterID(args.id).observe(viewLifecycleOwner) {
//            when (it) {
//                is Resource.Loading -> {
//                    Log.e("loo", "olo")
//                }
//                is Resource.Error -> {
//                    Log.e("tag", "Error Character ${it.massage.toString()}")
//                }
//                is Resource.Success -> {
//                    binding.tvName.text = it.data?.name
//                    it.data?.let { it1 -> binding.ivImage.setImage(it1.image) }
//                }
//            }
//        }
//    }
    }
}