package com.example.foodwastemangmentapplication1.donations

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DonationHistoryViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _donations = MutableStateFlow<List<Donation>>(emptyList())
    val donations: StateFlow<List<Donation>> = _donations

    init {
        db.collection("donations") // Firestore collection name
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    // handle the error as needed
                    return@addSnapshotListener
                }
                val list = snapshots?.documents?.mapNotNull { doc ->
                    doc.toObject(Donation::class.java)
                } ?: emptyList()
                _donations.value = list
            }
    }
}