package com.example.flagquizapp.play.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.FragmentResultBinding
import com.example.flagquizapp.play.PlayFragmentDirections

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    private val args: ResultFragmentArgs by navArgs()

    private val viewModelFactory: ScoreFactory by lazy { ScoreFactory(args.score) }
    private val viewModel: ResultViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel

        viewModel.navigateBackToMain.observe(viewLifecycleOwner, Observer {
            if(it){
                this.requireActivity().finish()
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}