package com.example.flagquizapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    // ALL LIVEDATA VARIABLES
    private var _navigateToPracticeFragment = MutableLiveData<Boolean>()
    private var _navigateToHighscoreFragment = MutableLiveData<Boolean>()
    private var _navigateToPlayActivity = MutableLiveData<Boolean>()
    // ALL GETTERS FOR VARIABLES
    val navigateToPracticeFragment : LiveData<Boolean>
        get() {
            return _navigateToPracticeFragment
        }
    val navigateToHighscoreFragment : LiveData<Boolean>
        get() {
            return _navigateToHighscoreFragment
        }
    val navigateToPlayActivity : LiveData<Boolean>
        get() {
            return _navigateToPlayActivity
        }

    // INIT
    init {
        _navigateToHighscoreFragment.value = false
        _navigateToPlayActivity.value = false
        _navigateToPracticeFragment.value = false
    }

    // NAVIGATION FUNCTIONS
    fun btnNavigateToPlayActivity(){
        _navigateToPlayActivity.value = true
    }
    fun btnNavigatePracticeFragmentClicked(){
        _navigateToPracticeFragment.value = true
    }
    fun btnNavigateHighscoreFragmentClicked(){
        _navigateToHighscoreFragment.value = true
    }

    fun navigateFinish(){
        _navigateToPracticeFragment.value = false
        _navigateToHighscoreFragment.value = false
        _navigateToPlayActivity.value = false
    }
}