package com.example.flagquizapp.countryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.flagquizapp.databinding.FragmentCountryDetailBinding


class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding
    private val args: CountryDetailFragmentArgs by navArgs()
    private val viewModelFactory: CountryDetailViewModelFactory by lazy { CountryDetailViewModelFactory(args.country) }
    private val viewModel: CountryDetailViewModel by viewModels {viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize binding
        binding = FragmentCountryDetailBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // load image of country
        val image = binding.imgFlag
        image.load(viewModel.country.value!!.flags!!.png)

        return binding.root
    }
}