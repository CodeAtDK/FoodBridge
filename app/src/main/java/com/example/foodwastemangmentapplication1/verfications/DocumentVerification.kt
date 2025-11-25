package com.example.foodwastemangmentapplication1.verfications

import java.security.Timestamp
import java.util.Date

data class DocumentVerification(
    val fileName: String = "",
    val downloadUrl: String = "",
    val status: String = "pending",
    val uploadedAt: Date? = null,
    val userId: String = ""
)
