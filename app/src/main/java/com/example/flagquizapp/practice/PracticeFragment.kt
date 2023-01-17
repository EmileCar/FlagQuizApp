package com.example.flagquizapp.practice

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.FragmentPracticeBinding
import com.example.flagquizapp.models.Country

class PracticeFragment : Fragment() {

    private lateinit var binding: FragmentPracticeBinding
    private lateinit var viewModel: PracticeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_practice, container, false)
        viewModel = ViewModelProvider(this).get(PracticeViewModel::class.java)
        binding.viewModel = viewModel

        // Setup adapter for recyclerview
        val adapter = CountryAdapter(CountryClickListener {
            viewModel.onCountryClicked(it)
        })
        binding.listCountries.adapter = adapter
        val manager = LinearLayoutManager(activity)
        binding.listCountries.layoutManager = manager

        // Observe the countries (everytime it changes, resubmit the recyclerview)
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        // Observe country
        viewModel.country.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateToCountryDetail(it)
                viewModel.navigateToDetailFinished()
            }
        })

        binding.listCountries.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        // niet wegdoen anders gaat de progressBar niet weg
        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_settings_africa -> viewModel.updateCountries("Africa")
            R.id.menu_settings_americas -> viewModel.updateCountries("Americas")
            R.id.menu_settings_asia -> viewModel.updateCountries("Asia")
            R.id.menu_settings_europe -> viewModel.updateCountries("Europe")
            R.id.menu_settings_oceania -> viewModel.updateCountries("Oceania")
            R.id.menu_settings_all -> viewModel.updateCountries("All")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToCountryDetail(country: Country) {
        requireView().findNavController().navigate(PracticeFragmentDirections.actionPracticeFragmentToCountryDetailFragment(country))
    }
}