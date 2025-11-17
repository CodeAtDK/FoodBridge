package com.example.foodwastemangmentapplication1.Market_Place

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ProductViewModel: ViewModel() {

    private val db = Firebase.firestore
    private val _products = mutableStateListOf<Product>()
    val products: List<Product> get() = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        db.collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val productList = documents.map { doc ->
                    Product(
                        id = doc.getString("id") ?: "",
                        name = doc.getString("name") ?: "",
                        price = doc.getDouble("price") ?: 0.0,
                        imageUrl = doc.getString("imageUrl") ?: "",
                        description = doc.getString("description") ?: ""
                    )
                }
                _products.clear()
                _products.addAll(productList)
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error fetching products", e)
            }
    }
}