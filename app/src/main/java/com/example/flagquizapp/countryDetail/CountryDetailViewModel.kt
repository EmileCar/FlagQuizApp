package com.example.flagquizapp.countryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.models.Country

class CountryDetailViewModel(var __country: Country): ViewModel() {
    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country>
        get() = _country

    init{
        _country.value = __country
    }

    fun convertCapitalsToString(): String{
        var string = ""
        if(country.value!!.capital != null){    // antarctica heeft bv geen capital
            string = country.value!!.capital!!.joinToString(", ")
        } else {
            string = "No capital"
        }
        return string
    }

}