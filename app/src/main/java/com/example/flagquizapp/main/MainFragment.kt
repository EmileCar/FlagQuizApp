package com.example.flagquizapp.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flagquizapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize binding
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        // NAVIGATE to play activity
        viewModel.navigateToPlayActivity.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToPlayActivity())
                viewModel.navigateFinish()
            }
        })

        // NAVIGATE to practice fragment
        viewModel.navigateToPracticeFragment.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToPracticeFragment())
                viewModel.navigateFinish()
            }
        })

        // NAVIGATE to highscore fragment
        viewModel.navigateToHighscoreFragment.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToHighscoreFragment())
                viewModel.navigateFinish()
            }
        })

        return binding.root
    }

    override fun onResume(){
        super.onResume()
        viewModel.navigateFinish();
    }


}