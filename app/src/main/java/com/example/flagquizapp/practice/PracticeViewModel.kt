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
    // ALL LIVEDATA VARIABLES
    private var _countryResponse = MutableLiveData<List<Country>?>()
    private var _countries = MutableLiveData<List<Country>?>()
    private var _country = MutableLiveData<Country?>()
    private var _error = MutableLiveData<String>()
    private var _loadingFinished = MutableLiveData<Boolean>()
    private var _onlyIndependent = MutableLiveData<Boolean>()
    private var storedRegion: String
    // GETTERS OF VARIABLES
    val countries: LiveData<List<Country>?>
        get() {
            return _countries
        }
    val country: LiveData<Country?>
        get() {
            return _country
        }
    val error: LiveData<String>
        get() {
            return _error
        }
    val loadingFinished : LiveData<Boolean>
        get() {
            return _loadingFinished
        }
    val onlyIndependent : LiveData<Boolean>
        get() {
            return _onlyIndependent
        }


    // INIT
    init{
        _error.value = ""
        _loadingFinished.value = false
        _onlyIndependent.value = true
        storedRegion = "All"

        viewModelScope.launch {
            try {
                _countryResponse.value  = CountryAPI.retrofitService.getCountries()
                updateCountries(storedRegion)
                _loadingFinished.value = true
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _loadingFinished.value = true

            }
        }
    }

    // Update the countries based on Region and Independent
    fun updateCountries(regionVal: String) {
        storedRegion = regionVal
        // store all countrydata in temporary variable
        var countriesTemp = _countryResponse.value!!
        // if only independent countries are selected, filter
        if(_onlyIndependent.value!!){
            countriesTemp = countriesTemp.filter { it.independent != null && it.independent }.toList()
        }

        // make second temporary variable and filter there on regions
        var countriesTemp2 = countriesTemp.filter { it.region != null && it.region.equals(storedRegion) }.toList()
        // if the second variable is empty, that means not a valid region has been passed. So show all countries (filtered on independent) instead
        if(!countriesTemp2.isEmpty()){
            countriesTemp = countriesTemp2
        }
        // set the countrie livedatavalue to the filtered countries
        _countries.value = countriesTemp
    }

    fun onCountryClicked(country: Country) {
        _country.value = country
    }

    fun navigateToDetailFinished() {
        _country.value = null
    }

    fun switchClicked(){
        _onlyIndependent.value = !_onlyIndependent.value!!
        updateCountries(storedRegion)
    }
}