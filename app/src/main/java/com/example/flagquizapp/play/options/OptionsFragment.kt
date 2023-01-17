package com.example.flagquizapp.play.options

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.flagquizapp.databinding.FragmentOptionsBinding


class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding
    private val viewModel: OptionsViewModel by viewModels<OptionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize binding
        binding = FragmentOptionsBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel

        // Navigate to play fragment
        viewModel.navigateToPlayFragment.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToPlayFragment())
                viewModel.navigateFinish()
            }
        })

        // Navigate back to main activity
        viewModel.navigateBackToMainActivity.observe(viewLifecycleOwner, Observer {
            if (it) {
                this.requireActivity().finish()
            }
        })

        // Set error when name is not valid
        viewModel.errorName.observe(viewLifecycleOwner, Observer {
            binding.editTextName.setError(it)
        })

        return binding.root
    }

    override fun onResume(){
        super.onResume()
        viewModel.navigateFinish()
    }

}