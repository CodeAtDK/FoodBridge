package com.example.foodwastemangmentapplication1.donationPickUps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.foodwastemangmentapplication1.donations.Donation
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class PickupViewModel : ViewModel() {

    var donations by mutableStateOf<List<Donation>>(emptyList())
    private val db = Firebase.firestore

    init {
        db.collection("donations")
            .addSnapshotListener { snapshots, _ ->
                if (snapshots != null) {
                    donations = snapshots.documents.map {
                        val data = it.toObject(Donation::class.java)?.copy(id = it.id)
                        data ?: Donation()
                    }
                }
            }
    }

    fun deleteDonation(id: String) {
        db.collection("donations").document(id).delete()
    }

    fun deleteDonationByDonorName(donorName: String) {
        db.collection("donations")
            .whereEqualTo("donorName", donorName)
            .get()
            .addOnSuccessListener { snapshots ->
                for (doc in snapshots.documents) {
                    doc.reference.delete()
                }
            }
    }


}