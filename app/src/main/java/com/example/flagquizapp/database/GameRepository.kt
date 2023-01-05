package com.example.flagquizapp.database

import androidx.lifecycle.LiveData


class GameRepository(private val gameDao: GameDao){

    val readAllData: LiveData<List<Game>> = gameDao.readAllData()

    suspend fun addGame(game: Game){
        gameDao.addGame(game)
    }
}