package com.example.flagquizapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Game>>
    private val repository: GameRepository

    init{
        val gameDao = GameDatabase.getDatabase(application).gameDao()
        repository = GameRepository(gameDao)
        readAllData = repository.readAllData
    }

    fun addGame(game: Game){
        viewModelScope.launch(Dispatchers.IO){
            repository.addGame(game)
        }
    }
}