package com.example.foodwastemangmentapplication1.Admin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.foodwastemangmentapplication1.verfications.DocumentVerification
import com.google.firebase.firestore.FirebaseFirestore

class AdminViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    var documents by mutableStateOf(listOf<DocumentVerification>())
        private set

    init {
        loadDocuments()
    }

    fun loadDocuments() {
        db.collectionGroup("files") // fetch all user files
            .addSnapshotListener { snapshot, e ->
                if (e != null) return@addSnapshotListener
                documents = snapshot?.toObjects(DocumentVerification::class.java) ?: emptyList()
            }
    }

    fun updateStatus(docId: String, userId: String, newStatus: String) {
        db.collection("document_verifications")
            .document(userId)
            .collection("files")
            .document(docId)
            .update("status", newStatus)
    }
}