package com.example.flagquizapp.practice

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.flagquizapp.R
import com.example.flagquizapp.databinding.DetailCountryInListBinding
import com.example.flagquizapp.models.Country


class CountryAdapter(val clickListener: CountryClickListener): ListAdapter<Country, CountryAdapter.ViewHolder>(ToDoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: DetailCountryInListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            country: Country,
            clickListener: CountryClickListener
        ) {
            val image = itemView.findViewById<ImageView>(R.id.imgFlag)
            image.load(country.flags!!.png!!)

            binding.country = country
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DetailCountryInListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class ToDoDiffCallback : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.cioc == newItem.cioc
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}

class CountryClickListener(val clickListener: (country: Country) -> Unit) {
    fun onClick(country: Country) = clickListener(country)
}