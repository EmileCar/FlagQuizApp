package com.example.flagquizapp.countryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.models.Country

class CountryDetailViewModel(__country: Country): ViewModel() {
    // COUNTRY LIVEDATA VARIABLE & GETTER
    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country>
        get() = _country

    // INIT
    init{
        _country.value = __country
    }

    // Convert the capitals of an object to string value
    //      - south africa has multiple capitals (why capital field is a List<String>)
    //      - antarctica does not have a capital (possible NullPointerException)
    fun convertCapitalsToString(): String{
        val string = if(country.value!!.capital != null){    // antarctica heeft bv geen capital
            country.value!!.capital!!.joinToString(", ")
        } else {
            "No capital"
        }
        return string
    }

}