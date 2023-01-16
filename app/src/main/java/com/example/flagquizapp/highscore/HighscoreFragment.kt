package com.example.flagquizapp.highscore

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
                binding.tvLegendName.visibility = View.INVISIBLE
                binding.tvLegendScore.visibility = View.INVISIBLE

                adapter.submitList(null)
            }
        })
        binding.listGames.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteHighScores()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteHighScores(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            gameViewModel.deleteAll()
            Toast.makeText(requireContext(), "Successfully removed the high scores", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete all high scores?")
        builder.setMessage("Are you sure you want to remove all high scores from this list?")
        builder.create().show()
    }
}