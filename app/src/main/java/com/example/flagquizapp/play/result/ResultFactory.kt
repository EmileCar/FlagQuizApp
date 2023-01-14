package com.example.flagquizapp.play.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flagquizapp.models.Country

class ScoreFactory(private val score: Int, private val guessedCountries: List<Country>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(score, guessedCountries) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}