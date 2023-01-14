package com.example.flagquizapp.database

import androidx.lifecycle.LiveData


class GameRepository(private val gameDao: GameDao){

    val readAllData: LiveData<List<Game>> = gameDao.readAllData()
    val readHighScores: LiveData<List<Game>> = gameDao.readHighScores()


    suspend fun addGame(game: Game){
        gameDao.addGame(game)
    }

    /*suspend fun addGuessedCountry(guessedCountry: GuessedCountry){
        gameDao.addGuessedCountry(guessedCountry)
    }*/
}