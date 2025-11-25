package com.example.foodwastemangmentapplication1.verfications

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


class DocumentUploadViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()

    fun uploadDocument(
        fileUri: Uri,
        fileName: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: return

        val fileRef = storage.reference.child("documents/$uid/$fileName")

        fileRef.putFile(fileUri)
            .addOnSuccessListener {
                fileRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    val docData = mapOf(
                        "fileName" to fileName,
                        "downloadUrl" to downloadUrl.toString(),
                        "status" to "pending",
                        "uploadedAt" to FieldValue.serverTimestamp(),
                        "userId" to uid
                    )

                    db.collection("document_verifications")
                        .document(uid)
                        .collection("files")
                        .add(docData)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure(it) }
                }
            }
            .addOnFailureListener { onFailure(it) }
    }
}

