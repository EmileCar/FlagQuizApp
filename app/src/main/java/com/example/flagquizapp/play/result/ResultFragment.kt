package com.example.flagquizapp.play.result

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.flagquizapp.database.Game
import com.example.flagquizapp.database.GameViewModel
import com.example.flagquizapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()
    private val viewModelFactory: ScoreFactory by lazy { ScoreFactory(args.score, args.guessedCountries.toList()) }
    private val viewModel: ResultViewModel by viewModels { viewModelFactory }
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize binding
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // Navigate back to main
        viewModel.navigateBackToMain.observe(viewLifecycleOwner, Observer {
            if(it){
                this.requireActivity().finish()
            }
        })

        // Check if data must be added to Room Database
        viewModel.dataAdded.observe(viewLifecycleOwner, Observer {
            if(!it){
                // Save game in Room database
                val game = Game(0, viewModel.name!!, viewModel.getPlayerScore())
                gameViewModel.addGame(game)
            }
        })

        return binding.root
    }

}