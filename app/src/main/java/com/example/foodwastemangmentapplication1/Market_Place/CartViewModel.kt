package com.example.foodwastemangmentapplication1.Market_Place

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

open class CartViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    var cartItems by mutableStateOf(listOf<Product>())

    var totalPrice by mutableStateOf(0.0)

    init {
        loadCart()
    }

    fun loadCart() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("carts").document(uid).collection("items")
            .addSnapshotListener { snapshot, e ->
                if (e != null) return@addSnapshotListener
                val products = snapshot?.toObjects(Product::class.java) ?: emptyList()
                cartItems = products
                totalPrice = products.sumOf { it.price }
            }
    }

    fun removeFromCart(productId: String) {
        val uid = auth.currentUser?.uid ?: return
        db.collection("carts").document(uid).collection("items")
            .document(productId).delete()
    }

    fun buyNow(onSuccess: () -> Unit) {
        val uid = auth.currentUser?.uid ?: return
        // Example: clear cart after purchase
        db.collection("carts").document(uid).collection("items")
            .get().addOnSuccessListener { snapshot ->
                snapshot?.documents?.forEach { it.reference.delete() }
                onSuccess()
            }
    }
}