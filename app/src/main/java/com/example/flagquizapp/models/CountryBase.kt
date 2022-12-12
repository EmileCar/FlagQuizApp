package com.example.flagquizapp.models

data class CountryBase(
    val errors : List<String>?,
    val results : Int,
    val response: List<Country>
)