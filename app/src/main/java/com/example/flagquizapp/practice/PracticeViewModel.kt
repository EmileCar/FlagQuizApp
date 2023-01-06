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

    var onlyIndependent = MutableLiveData<Boolean>()

    fun removeDependent(countries: List<Country>): List<Country>{
        return countries.filter { it.independent != null && it.independent }.toList()
    }

    init{
        _error.value = ""
        _loadingFinished.value = false
        onlyIndependent.value = false
        Log.d("MIJNPROBLEEM!", "practice-viewmodel-init")

        viewModelScope.launch {
            try {
                _countryResponse.value  = CountryAPI.retrofitService.getCountries()
                _countryResponse.value = removeDependent(_countryResponse.value!!)
                _loadingFinished.value = true

                Log.d("MIJNPROBLEEM!", _countryResponse.value!!.size.toString())
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _loadingFinished.value = true

                Log.d("MIJNPROBLEEM!", e.localizedMessage)
            }
        }
    }

    fun onCountryClicked(country: Country) {
        _country.value = country
    }

    fun navigateToDetailFinished() {
        _country.value = null
    }

    fun switchClicked(){
        _countryResponse.value = null
        onlyIndependent.value = !onlyIndependent.value!!
        Log.d("MIJNPROBLEEM!", onlyIndependent.value.toString())
    }
}