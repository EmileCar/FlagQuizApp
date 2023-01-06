package com.example.flagquizapp.practice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flagquizapp.R
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_practice, container, false)
        viewModel = ViewModelProvider(this).get(PracticeViewModel::class.java)
        binding.viewModel = viewModel

        val adapter = CountryAdapter(CountryClickListener {
            viewModel.onCountryClicked(it)
        })
        binding.listCountries.adapter = adapter

        val manager = LinearLayoutManager(activity)
        binding.listCountries.layoutManager = manager

        viewModel.countryResponse.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        viewModel.country.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateToCountryDetail(it)
                viewModel.navigateToDetailFinished()
            }
        })

        viewModel.onlyIndependent.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("MIJNPROB", "test")
            }
        })

        binding.listCountries.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        // niet wegdoen anders gaat de progressBar niet weg
        binding.setLifecycleOwner(this)

        return binding.root;
    }

    fun navigateToCountryDetail(country: Country) {
        requireView().findNavController().navigate(PracticeFragmentDirections.actionPracticeFragmentToCountryDetailFragment(country))
    }
}