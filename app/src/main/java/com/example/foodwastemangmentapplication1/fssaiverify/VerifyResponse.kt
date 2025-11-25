package com.example.foodwastemangmentapplication1.fssaiverify

import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    @SerializedName("status") val status: String?,          // e.g., "active"
    @SerializedName("business_name") val businessName: String?,
    @SerializedName("expiry") val expiry: String?,
    @SerializedName("message") val message: String?
)