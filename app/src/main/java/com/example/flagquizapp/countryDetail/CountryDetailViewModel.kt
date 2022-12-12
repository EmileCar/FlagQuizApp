package com.example.flagquizapp.countryDetail

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
        viewModelScope.launch {
            try {
                _countryResponse.value  = CountryAPI.retrofitService.getCountryData("Belgium")
                _loadingFinished.value = true
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _loadingFinished.value = true
                //print(e.localizedMessage)
            }
        }
    }


}