package com.example.flagquizapp.play.options

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.FragmentOptionsBinding
import com.example.flagquizapp.main.MainViewModel

class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding
    private val viewModel: OptionsViewModel by viewModels<OptionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel

        viewModel.navigateToPlayFragment.observe(viewLifecycleOwner, Observer {
            if (it) {
                Log.d("MIJNPROBLEEM!", "IE GAAT NAAR PLAYFRAGMENT")
                findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToPlayFragment())
                viewModel.navigateFinish()
            }
        })

        viewModel.errorName.observe(viewLifecycleOwner, Observer {
            binding.editTextName.setError(it)
        })

        viewModel.navigateBackToMainActivity.observe(viewLifecycleOwner, Observer {
            if (it) {
                this.requireActivity().finish()
            }
        })

        return binding.root
    }

    override fun onResume(){
        Log.d("MIJNPROBLEEM", "ONRESUME")
        super.onResume()
        viewModel.navigateFinish();
    }
}