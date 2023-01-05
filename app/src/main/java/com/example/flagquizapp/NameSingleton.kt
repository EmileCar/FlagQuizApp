package com.example.flagquizapp

class NameSingleton {
    init {
        instance = this
    }
    companion object {
        private var instance: NameSingleton? = null
        fun instance() : NameSingleton {
            if (instance == null) {
                instance = NameSingleton()
            }
            return instance as NameSingleton
        }
    }
    var name : String? = null
}