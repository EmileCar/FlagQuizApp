package com.example.flagquizapp.countryDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagquizapp.models.Country
import com.example.flagquizapp.network.CountryAPI
import kotlinx.coroutines.launch

class CountryDetailViewModel: ViewModel() {
    private var _countryResponse = MutableLiveData<Country?>()
    val countryResponse: LiveData<Country?>
        get() {
            return _countryResponse
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
        Log.d("MIJNPROBLEEM!", "viewmodel-init")

        viewModelScope.launch {
            try {
                // we zetten er [0] achter door de opbouw van de json, de data van de API is nog eens omringt in een Array
                _countryResponse.value  = CountryAPI.retrofitService.getCountryData("belgium")[0]
                Log.d("MIJNPROBLEEM!", _countryResponse.value!!.name!!.common!!)
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


}