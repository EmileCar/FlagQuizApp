package com.example.flagquizapp.countryDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagquizapp.models.Country
import com.example.flagquizapp.network.CountryAPI
import kotlinx.coroutines.launch

class CountryDetailViewModel(var __country: Country): ViewModel() {
    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country>
        get() = _country

    init{
        _country.value = __country
    }


}