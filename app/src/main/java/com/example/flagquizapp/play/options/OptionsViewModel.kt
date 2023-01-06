package com.example.flagquizapp.play.options

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.NameSingleton

class OptionsViewModel: ViewModel() {
    var name = MutableLiveData<String>()
    var errorName = MutableLiveData<String?>()
    var isInitialised = MutableLiveData<Boolean>()

    private var _navigateToPlayFragment = MutableLiveData<Boolean>()
    val navigateToPlayFragment : LiveData<Boolean>
        get() {
            return _navigateToPlayFragment
        }

    private var _navigateBackToMainActivity = MutableLiveData<Boolean>()
    val navigateBackToMainActivity : LiveData<Boolean>
        get() {
            return _navigateBackToMainActivity
        }

    init {
        _navigateBackToMainActivity.value = false
        isInitialised.value = false
        name.value = ""
        errorName.value = null
    }

    fun btnNavigateBackToMainActivityClicked(){
        _navigateBackToMainActivity.value = true
    }

    fun navigateFinish(){
        _navigateToPlayFragment.value = false
    }

    fun btnClickPlay(){
        Log.d("MIJNPROBLEEM", name.value!!)
        name.value = name.value!!.trim()
        if(name.value == ""){
            errorName.value = "Name can not be empty"
        } else if (name.value!!.length < 3){
            errorName.value = "Please provide more than 2 characters"
        } else if (name.value!!.length > 15){
            errorName.value = "Please provide less than 15 characters"
        } else {
            NameSingleton.instance().name = name.value
            _navigateToPlayFragment.value = true
        }
    }
}