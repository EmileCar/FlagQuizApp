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

    private var _navigateToPracticeFragment = MutableLiveData<Boolean>()
    val navigateToPracticeFragment : LiveData<Boolean>
        get() {
            return _navigateToPracticeFragment
        }

    fun btnNavigateCountryDetailFragmentClicked(){
        _navigateToCountryDetailFragment.value = true
    }

    fun btnNavigatePracticeFragmentClicked(){
        _navigateToPracticeFragment.value = true
    }

    fun navigateFinish(){
        _navigateToCountryDetailFragment.value = false
        _navigateToPracticeFragment.value = false
    }
}