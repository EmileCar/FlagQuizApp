package com.example.flagquizapp.countryDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.FragmentCountryDetailBinding
import com.example.flagquizapp.databinding.FragmentMainBinding
import com.example.flagquizapp.main.MainViewModel


class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding
    private lateinit var viewModel: CountryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(CountryDetailViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }
}