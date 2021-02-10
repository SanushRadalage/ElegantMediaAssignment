package com.sanush.elegantmedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val address: String,
    val description: String,
    val id: Int,
    val image: Image,
    val latitude: String,
    val longitude: String,
    val phoneNumber: String,
    val postcode: String,
    val title: String
) : Parcelable

@Parcelize
data class Image(
    val large: String,
    val medium: String,
    val small: String
) : Parcelable