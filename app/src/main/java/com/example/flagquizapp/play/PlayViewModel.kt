package com.example.flagquizapp.play

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flagquizapp.NameSingleton

class PlayViewModel(): ViewModel() {
    private var _name = MutableLiveData<String>()

    val name : LiveData<String>
        get() {
            return _name
        }

    init {
        _name.value = NameSingleton.instance().name
    }

}