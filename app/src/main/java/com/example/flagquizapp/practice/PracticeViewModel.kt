package com.example.flagquizapp.practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagquizapp.models.Country
import com.example.flagquizapp.network.CountryAPI
import kotlinx.coroutines.launch

class PracticeViewModel: ViewModel(){
    private var _countryResponse = MutableLiveData<List<Country>?>()
    val countryResponse: LiveData<List<Country>?>
        get() {
            return _countryResponse
        }

    private var _country = MutableLiveData<Country?>()
    val country: LiveData<Country?>
        get() {
            return _country
        }

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() {
            return _error
        }

    private var _loadingFinished = MutableLiveData<Boolean>()
    val loadingFinished : LiveData<Boolean>
        get() {
            return _loadingFinished
        }

    init{
        _error.value = ""
        _loadingFinished.value = false
        Log.d("MIJNPROBLEEM!", "practice-viewmodel-init")

        viewModelScope.launch {
            try {
                // we zetten er [0] achter door de opbouw van de json, de data van de API is nog eens omringt in een Array
                _countryResponse.value  = CountryAPI.retrofitService.getCountries()
                Log.d("MIJNPROBLEEM!", _countryResponse.value!!.size.toString())
                _loadingFinished.value = true
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _loadingFinished.value = true
                Log.d("MIJNPROBLEEM!", "EMPTY")
                Log.d("MIJNPROBLEEM!", e.localizedMessage)

                //print(e.localizedMessage)
            }
        }
    }

    fun onCountryClicked(country: Country) {
        _country.value = country
    }
}