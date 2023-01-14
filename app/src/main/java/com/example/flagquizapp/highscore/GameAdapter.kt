package com.example.flagquizapp.highscore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flagquizapp.database.Game
import com.example.flagquizapp.databinding.GameInHighscoreListBinding

class GameAdapter: ListAdapter<Game, GameAdapter.ViewHolder>(ToDoDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: GameInHighscoreListBinding) : RecyclerView.ViewHolder(binding.root){

        private var pos = 1

        fun bind(
            game: Game,
        ) {
            binding.tvPosition.text = pos.toString()
            binding.game = game
            binding.executePendingBindings()
            pos++
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GameInHighscoreListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class ToDoDiffCallback : DiffUtil.ItemCallback<Game>() {

    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }
}