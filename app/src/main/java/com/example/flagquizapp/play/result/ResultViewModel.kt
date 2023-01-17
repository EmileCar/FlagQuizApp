package com.example.flagquizapp.play.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.NameSingleton
import com.example.flagquizapp.models.Country

class ResultViewModel(var __score: Int, var __guessedCountries: List<Country>): ViewModel() {
    // ALL (LiveData) VARIABLES
    var name = NameSingleton.instance().name
    private val _score = MutableLiveData<Int>()
    private var _dataAdded = MutableLiveData<Boolean>()
    private var _navigateBackToMain = MutableLiveData<Boolean>()
    private var _guessedCountries = MutableLiveData<List<Country>>()
    // ALL GETTERS FOR VARIABLES
    val score : LiveData<Int>
        get() = _score
    val dataAdded : LiveData<Boolean>
        get() {
            return _dataAdded
        }
    val navigateBackToMain : LiveData<Boolean>
        get() {
            return _navigateBackToMain
        }
    val guessedCountries : LiveData<List<Country>>
        get() {
            return _guessedCountries
        }

    init {
        _score.value = __score
        _guessedCountries.value = __guessedCountries
        _navigateBackToMain.value = false
        _dataAdded.value = false
    }

    // Handle button click to main
    fun btnClickBackToMain(){
        _navigateBackToMain.value = true
    }

    // Getter for playerscore
    fun getPlayerScore(): Int{
        return _score.value!!
    }

}