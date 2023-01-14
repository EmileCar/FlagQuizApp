package com.example.flagquizapp.highscore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flagquizapp.R
import com.example.flagquizapp.database.GameViewModel
import com.example.flagquizapp.databinding.FragmentHighscoreBinding
import com.example.flagquizapp.practice.CountryAdapter
import com.example.flagquizapp.practice.CountryClickListener

class HighscoreFragment : Fragment() {

    private lateinit var binding: FragmentHighscoreBinding
    private lateinit var viewModel: HighScoreViewModel
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_highscore, container, false)
        viewModel = ViewModelProvider(this).get(HighScoreViewModel::class.java)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel

        // setup the recyclerview / adapter
        val adapter = GameAdapter()
        binding.listGames.adapter = adapter
        val manager = LinearLayoutManager(activity)
        binding.listGames.layoutManager = manager

        // get all the high scores from database and submit to the recyclerview
        gameViewModel.readHighScores.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                it?.let{
                    adapter.submitList(it)
                }
            } else {
                binding.tvNoGamesYet.visibility = View.VISIBLE
            }
        })
        binding.listGames.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.lifecycleOwner = this

        return binding.root
    }
}