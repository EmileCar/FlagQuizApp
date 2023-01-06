package com.example.flagquizapp.play.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.NameSingleton

class ResultViewModel(var __score: Int): ViewModel() {
    var name = NameSingleton.instance().name
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private var _navigateBackToMain = MutableLiveData<Boolean>()
    val navigateBackToMain : LiveData<Boolean>
        get() {
            return _navigateBackToMain
        }

    init {
        _score.value = __score
        _navigateBackToMain.value = false
    }

    fun btnClickBackToMain(){
        _navigateBackToMain.value = true
    }

}