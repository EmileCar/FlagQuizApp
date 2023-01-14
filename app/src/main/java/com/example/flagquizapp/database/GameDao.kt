package com.example.flagquizapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flagquizapp.database.Game
import com.example.flagquizapp.models.Country

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGame(game: Game)

    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGuessedCountry(guessedCountry: GuessedCountry)*/

    @Query("SELECT * FROM game_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Game>>

    @Query("SELECT * FROM game_table ORDER BY score DESC LIMIT 20")
    fun readHighScores(): LiveData<List<Game>>




}