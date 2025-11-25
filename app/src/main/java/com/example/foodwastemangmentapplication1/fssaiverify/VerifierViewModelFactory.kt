package com.example.foodwastemangmentapplication1.fssaiverify

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class VerifierViewModelFactory(
    private val api: AttestrApi,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VerifierViewModel::class.java)) {
            return VerifierViewModel(api, context as () -> Context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
