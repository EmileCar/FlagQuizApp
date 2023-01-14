package com.example.flagquizapp.play

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagquizapp.NameSingleton
import com.example.flagquizapp.models.Country
import com.example.flagquizapp.network.CountryAPI
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.launch

class PlayViewModel(): ViewModel() {
    private var index = 0
    private var countries =  MutableLiveData<List<Country>?>()
    private var guessedCountries: MutableList<Country> = mutableListOf()
    var currentCountry = MutableLiveData<Country?>()
    var message = MutableLiveData<String?>()
    var guess = MutableLiveData<String?>()
    var name = NameSingleton.instance().name

    private var _navigateToResultFragment = MutableLiveData<Boolean>()
    val navigateToResultFragment : LiveData<Boolean>
        get() {
            return _navigateToResultFragment
        }

    private var _countryResponse = MutableLiveData<List<Country>?>()
    val countryResponse: LiveData<List<Country>?>
        get() {
            return _countryResponse
        }

    private var _loadingFinished = MutableLiveData<Boolean>()
    val loadingFinished : LiveData<Boolean>
        get() {
            return _loadingFinished
        }

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() {
            return _error
        }

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() {
            return _score
        }

    private var _wrongGuess = MutableLiveData<String?>()
    val wrongGuess : LiveData<String?>
        get() {
            return _wrongGuess
        }

    private var _allCountriesPassed = MutableLiveData<Boolean>()
    val allCountriesPassed : LiveData<Boolean>
        get() {
            return _allCountriesPassed
        }


    init {
        // _name.value = NameSingleton.instance().name
        message.value = ""
        guess.value = ""
        _error.value = ""
        _score.value = 0
        _loadingFinished.value = false
        _allCountriesPassed.value = false
        _navigateToResultFragment.value = false
        getRandomCountries()
    }

    fun removeDependent(countries: List<Country>): List<Country>{
        return countries.filter { it.independent != null && it.independent }.toList()
    }

    fun getRandomCountries(){
        viewModelScope.launch {
            try {
                _countryResponse.value  = CountryAPI.retrofitService.getCountries()
                countries.value = removeDependent(_countryResponse.value!!).shuffled()
                Log.d("MIJNPROBLEEM!", countries.value!!.size.toString())
                _loadingFinished.value = true
                currentCountry.value = countries.value!![index]
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _loadingFinished.value = true
                Log.d("MIJNPROBLEEM!", e.localizedMessage)
            }
        }
    }

    fun btnClickSkip(){
        showNextCountry()
        Log.d("MIJNPROBLEEM!", currentCountry.value!!.name!!.common!!)
    }

    fun btnClickGuess(){
        Log.d("MIJNPROBLEEM!", currentCountry.value!!.name!!.common!!)
        if(guess.value!!.toLowerCase().trim().equals(currentCountry.value!!.name!!.common!!.toLowerCase().trim())){
            _score.value = _score.value!! + 1
            guessedCountries.add(currentCountry.value!!)
            message.value = "You guessed " + currentCountry.value!!.name!!.common + " correctly!"
            showNextCountry()
        } else {
            _wrongGuess.value = "Wrong guess"
        }
    }

    fun btnClickResult(){
        _navigateToResultFragment.value = true
    }

    fun getScore(): Int{
        return _score.value!!
    }

    fun getGuessedCountries(): List<Country>{
        Log.d("MIJNPROBLEEM", ": " + guessedCountries!!.toString())
        // TODO: NULLPOINTEREXCEPTION wanneer guessedCountries leeg is
        return guessedCountries!!
    }

    fun navigateFinish(){
        _navigateToResultFragment.value = false
    }

    private fun showNextCountry(){
        index++

        if(index < countries.value!!.size){
            currentCountry.value = countries.value!![index];
        } else {
            _allCountriesPassed.value = true
        }

        guess.value = "";
    }


}