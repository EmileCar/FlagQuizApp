package com.example.flagquizapp.play

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.FragmentPlayBinding
import com.example.flagquizapp.main.MainFragmentDirections

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding
    private val viewModel: PlayViewModel by viewModels<PlayViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loadingFinished.observe(viewLifecycleOwner, Observer {
            if(it!!){
                binding.imgFlagInQuiz.visibility = View.VISIBLE;
                binding.btnGuessCountry.visibility = View.VISIBLE
                binding.btnSkipCountry.visibility = View.VISIBLE
                binding.editTextGuess.visibility = View.VISIBLE
                binding.btnToResult.visibility = View.VISIBLE

            } else {
                binding.imgFlagInQuiz.visibility = View.INVISIBLE;
                binding.btnGuessCountry.visibility = View.INVISIBLE
                binding.btnSkipCountry.visibility = View.INVISIBLE
                binding.editTextGuess.visibility = View.INVISIBLE
                binding.btnToResult.visibility = View.INVISIBLE
            }
        })

        viewModel.currentCountry.observe(viewLifecycleOwner, Observer {
            binding.imgFlagInQuiz.load(it!!.flags!!.png!!)
        })

        viewModel.wrongGuess.observe(viewLifecycleOwner, Observer {
            binding.editTextGuess.setError(it)
        })

        viewModel.score.observe(viewLifecycleOwner, Observer {
            binding.tvScore.text = "Score:  " + it!!
        })

        viewModel.allCountriesPassed.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.btnToResult.text = "See results"
            } else {
                binding.btnToResult.text = "Give up"
            }
        })

        viewModel.navigateToResultFragment.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(PlayFragmentDirections.actionPlayFragmentToResultFragment(viewModel.getScore()))
                viewModel.navigateFinish()
            }
        })

        @Override fun onBackPressed(){
            Log.d("MIJNPROBLEEM", "PRESSBACK")
        }

        return binding.root
    }

}