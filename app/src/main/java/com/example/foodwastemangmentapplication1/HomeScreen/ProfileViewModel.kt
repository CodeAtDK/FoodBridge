package com.example.foodwastemangmentapplication1.HomeScreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val name: String = "",
    val email: String = "",
    val phone: String = ""
)

class ProfileViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _profile = MutableStateFlow(UserProfile())
    val profile: StateFlow<UserProfile> = _profile

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadProfile()
    }

    fun loadProfile() {
        val uid = auth.currentUser?.uid ?: return
        _loading.value = true

        db.collection("users").document(uid)
            .get()
            .addOnSuccessListener { snapshot ->
                val user = snapshot.toObject(UserProfile::class.java)
                if (user != null) {
                    _profile.value = user
                }
                _loading.value = false
            }
            .addOnFailureListener { e ->
                _error.value = e.message
                _loading.value = false
                Log.e("ProfileViewModel", "Error loading profile", e)
            }
    }

    fun updateProfile(name: String, email: String, phone: String) {
        val uid = auth.currentUser?.uid ?: return
        _loading.value = true

        val updatedProfile = UserProfile(name, email, phone)

        db.collection("users").document(uid)
            .set(updatedProfile)
            .addOnSuccessListener {
                _profile.value = updatedProfile
                _loading.value = false
            }
            .addOnFailureListener { e ->
                _error.value = e.message
                _loading.value = false
                Log.e("ProfileViewModel", "Error updating profile", e)
            }
    }
}