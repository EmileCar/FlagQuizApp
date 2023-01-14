package com.example.flagquizapp.play.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.NameSingleton
import com.example.flagquizapp.models.Country

class ResultViewModel(var __score: Int, var __guessedCountries: List<Country>): ViewModel() {
    var name = NameSingleton.instance().name
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private var _navigateBackToMain = MutableLiveData<Boolean>()
    val navigateBackToMain : LiveData<Boolean>
        get() {
            return _navigateBackToMain
        }

    private var _guessedCountries = MutableLiveData<List<Country>>()
    val guessedCountries : LiveData<List<Country>>
        get() {
            return _guessedCountries
        }

    init {
        _score.value = __score
        _guessedCountries.value = __guessedCountries
        _navigateBackToMain.value = false
    }

    fun btnClickBackToMain(){
        _navigateBackToMain.value = true
    }

    fun getPlayerScore(): Int{
        return _score.value!!
    }

}