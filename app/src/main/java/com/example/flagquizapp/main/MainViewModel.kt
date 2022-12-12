package com.example.flagquizapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _navigateToCountryDetailFragment = MutableLiveData<Boolean>()
    val navigateToCountryDetailFragment : LiveData<Boolean>
        get() {
            return _navigateToCountryDetailFragment
        }

    fun btnNavigateCountryDetailFragmentClicked(){
        _navigateToCountryDetailFragment.value = true
    }

    fun navigateFinish(){
        _navigateToCountryDetailFragment.value = false
    }
}