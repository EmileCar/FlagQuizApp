package com.example.flagquizapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var flagQuiz: Boolean,
    var size: Int,
    var region: String,
    var onlyIndependentCountries: Boolean,
    var score: Int
){

}