package com.example.flagquizapp.play.options

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.NameSingleton

class OptionsViewModel: ViewModel() {
    var name = MutableLiveData<String>()
    var errorName = MutableLiveData<String?>()
    private var mockupName = MutableLiveData<String?>()


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
        name.value = ""
        errorName.value = null
        mockupName.value = null
    }

    fun btnNavigateBackToMainActivityClicked(){
        _navigateBackToMainActivity.value = true
    }

    fun navigateFinish(){
        _navigateToPlayFragment.value = false
    }

    fun btnClickPlay(){
        Log.d("MIJNPROBLEEM", name.value!!)
        if(name.value == ""){
            errorName.value = "Naam mag niet leeg zijn"
        } else if (name.value!!.length < 3){
            errorName.value = "Gelieve meer dan 2 letters te voorzien"
        } else if (name.value!!.length > 15){
            errorName.value = "Gelieve minder dan 15 letters te voorzien"
        } else {
            NameSingleton.instance().name = name.value
            _navigateToPlayFragment.value = true
            Log.d("MIJNPROBLEEM!", "VELDEN ZIJN LEEg")
        }
    }
}