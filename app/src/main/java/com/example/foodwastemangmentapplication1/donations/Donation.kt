package com.example.foodwastemangmentapplication1.donations

data class Donation(
    val id: String = "",
    val donorName: String = "",
    val contact: String = "",
    val foodDetails: String = "",
    val packaged: Boolean = true, // New field
    val latitude: Double? = null,
    val longitude: Double? = null
//    val id: String = UUID.randomUUID().toString(), // Added a unique key for LazyColumn items
//    val date: String,
//    val items: String,
//    val quantity: Boolean
)