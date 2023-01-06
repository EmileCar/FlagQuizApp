package com.example.flagquizapp.play.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreFactory(private val score: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(score) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}