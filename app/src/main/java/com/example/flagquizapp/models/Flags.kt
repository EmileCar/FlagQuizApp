package com.example.flagquizapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flags(
    val png: String?,
    val svg: String?
) : Parcelable