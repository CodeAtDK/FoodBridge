package com.example.foodwastemangmentapplication1.donations

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class DonationViewModel(application: Application) : AndroidViewModel(application) {
    private val db = FirebaseFirestore.getInstance()
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    fun addDonation(donation: Donation) {
        val collection = if (donation.packaged) "donations" else "food_management"

        viewModelScope.launch {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    val donationWithLocation = donation.copy(
                        latitude = location?.latitude,
                        longitude = location?.longitude,


                    )
                    Log.d("AddDonationScreen", "$donationWithLocation")
                    db.collection(collection).add(donationWithLocation)
                    Log.d("latitude", "${location?.latitude}")
                    Log.d("latitude", "${location?.longitude}")
                }
        }
    }
}