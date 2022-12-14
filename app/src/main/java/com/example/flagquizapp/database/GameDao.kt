package com.example.flagquizapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flagquizapp.database.Game

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGame(game: Game)

    @Query("SELECT * FROM game_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Game>>
}