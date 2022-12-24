package com.example.flagquizapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    val common: String?,
    //val nativeName: NativeName,       // we gebruiken enkel de common name
    val official: String?
) : Parcelable