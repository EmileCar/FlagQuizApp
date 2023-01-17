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
    // ALL (LIVEDATA) VARIABLES
    private var index = 0
    private var countries =  MutableLiveData<List<Country>?>()
    private var guessedCountries: MutableList<Country> = mutableListOf()
    private var _navigateToResultFragment = MutableLiveData<Boolean>()
    private var _countryResponse = MutableLiveData<List<Country>?>()
    private var _loadingFinished = MutableLiveData<Boolean>()
    private var _error = MutableLiveData<String>()
    private var _score = MutableLiveData<Int>()
    private var _wrongGuess = MutableLiveData<String?>()
    private var _allCountriesPassed = MutableLiveData<Boolean>()
    var currentCountry = MutableLiveData<Country?>()
    var message = MutableLiveData<String?>()
    var guess = MutableLiveData<String?>()
    val name = NameSingleton.instance().name
    // GETTERS OF VARIABLES
    val navigateToResultFragment : LiveData<Boolean>
        get() {
            return _navigateToResultFragment
        }
    val loadingFinished : LiveData<Boolean>
        get() {
            return _loadingFinished
        }
    val error: LiveData<String>
        get() {
            return _error
        }
    val score : LiveData<Int>
        get() {
            return _score
        }
    val wrongGuess : LiveData<String?>
        get() {
            return _wrongGuess
        }
    val allCountriesPassed : LiveData<Boolean>
        get() {
            return _allCountriesPassed
        }

    // INIT
    init {
        message.value = ""
        guess.value = ""
        _error.value = ""
        _score.value = 0
        _loadingFinished.value = false
        _allCountriesPassed.value = false
        _navigateToResultFragment.value = false
        getRandomCountries()
    }

    // Remove non independent countries from list
    fun removeDependent(countries: List<Country>): List<Country>{
        return countries.filter { it.independent != null && it.independent }.toList()
    }

    // Get countries from API
    fun getRandomCountries(){
        viewModelScope.launch {
            try {
                _countryResponse.value  = CountryAPI.retrofitService.getCountries()
                countries.value = removeDependent(_countryResponse.value!!).shuffled()
                _loadingFinished.value = true
                // Show the first country
                currentCountry.value = countries.value!![index]
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _loadingFinished.value = true
            }
        }
    }

    // Handle skip button click event
    fun btnClickSkip(){
        showNextCountry()
    }

    // Handle guess button click event
    fun btnClickGuess(){
        // if name of guess is the same as currentcountry
        //      - increment score
        //      - add country to guessedcountries list
        //      - generate a message (for Toast)
        //      - display the next country in list
        if(guess.value!!.toLowerCase().trim().equals(currentCountry.value!!.name!!.common!!.toLowerCase().trim())){
            _score.value = _score.value!! + 1
            guessedCountries.add(currentCountry.value!!)
            message.value = "You guessed " + currentCountry.value!!.name!!.common + " correctly!"
            showNextCountry()
        } else {
            _wrongGuess.value = "Wrong guess"
        }
    }

    // Handle result button click event
    fun btnClickResult(){
        _navigateToResultFragment.value = true
    }

    private fun showNextCountry(){
        index++

        // check if all countries have passed
        if(index < countries.value!!.size){
            currentCountry.value = countries.value!![index];
        } else {
            _allCountriesPassed.value = true
        }

        // reset the guess value in the editTextBox
        guess.value = "";
    }

    fun getScore(): Int{
        return _score.value!!
    }

    fun getGuessedCountries(): List<Country>{
        return guessedCountries!!
    }

    fun navigateFinish(){
        _navigateToResultFragment.value = false
    }




}