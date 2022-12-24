package com.example.flagquizapp.countryDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.FragmentCountryDetailBinding
import com.example.flagquizapp.databinding.FragmentMainBinding
import com.example.flagquizapp.main.MainViewModel


class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding

    private val args: CountryDetailFragmentArgs by navArgs()

    private val viewModelFactory: CountryDetailViewModelFactory by lazy {
        CountryDetailViewModelFactory(args.country)
    }

    private val viewModel: CountryDetailViewModel by viewModels {viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel

        //val country = CountryDetailFragmentArgs.fromBundle(requireArguments()).country

        return binding.root
    }
}