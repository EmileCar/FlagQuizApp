package com.example.flagquizapp.highscore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.database.Game
import com.example.flagquizapp.database.GameViewModel

class HighScoreViewModel: ViewModel() {
    private var _gameResponse = MutableLiveData<List<Game>>()
    val gameResponse: LiveData<List<Game>>
        get() {
            return _gameResponse
        }
    var position = MutableLiveData<Int?>()


    init {
        position.value = 0
    }

    fun deleteHighScores(){

    }


}