package com.example.flagquizapp.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flagquizapp.practice.CountryAdapter
import com.example.flagquizapp.practice.CountryClickListener
import com.example.flagquizapp.databinding.FragmentPracticeBinding
import com.example.flagquizapp.models.Country

class PracticeFragment : Fragment() {

    private lateinit var binding: FragmentPracticeBinding
    private lateinit var viewModel: PracticeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPracticeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(PracticeViewModel::class.java)
        binding.viewModel = viewModel

        binding.listCountries.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val adapter = CountryAdapter(CountryClickListener {
            viewModel.onCountryClicked(it)
        })

        viewModel.countryResponse.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        viewModel.country.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateToCountryDetail(it)
            }
        })

        binding.listCountries.adapter = adapter
        val manager = LinearLayoutManager(activity)
        binding.listCountries.layoutManager = manager


        return binding.root;
    }

    fun navigateToCountryDetail(country: Country) {
        requireView().findNavController().navigate(PracticeFragmentDirections.actionPracticeFragmentToCountryDetailFragment(country))
    }
}