package com.example.foodwastemangmentapplication1.donations

data class Donation(
    val id: String = "",
    val donorName: String = "",
    val contact: String = "",
    val foodDetails: String = "",
    val packaged: Boolean = true, // New field
    val latitude: Double? = null,
    val longitude: Double? = null

)