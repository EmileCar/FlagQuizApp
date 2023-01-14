package com.example.flagquizapp.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.flagquizapp.models.Country

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var score: Int
){}

@Entity(tableName = "guessedCountry_table")
data class GuessedCountry(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var gameId: Int,
){

}