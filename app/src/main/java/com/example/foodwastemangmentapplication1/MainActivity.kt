package com.example.foodwastemangmentapplication1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodwastemangmentapplication1.LoginProcess.LoginScreen
import com.example.foodwastemangmentapplication1.NavigationController.Nav
//import androidx.lint.kotlin.metadata.Visibility
import com.example.foodwastemangmentapplication1.ui.theme.FoodWasteMangmentApplication1Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore




class MainActivity : ComponentActivity() {

//    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodWasteMangmentApplication1Theme {

                firebaseAuth = Firebase.auth
                addSampleProducts()
                Nav()
            }
        }
    }
    fun showToastMessage() {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
    }

    val db = Firebase.firestore

    val sampleProducts = listOf(
        hashMapOf(
            "id" to "1",
            "name" to "Vintage Leather Backpack",
            "price" to 79.99,
            "imageUrl" to "https://images.unsplash.com/photo-1553062407-98eeb68c6a62?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&q=60",
            "description" to "A stylish and durable vintage leather backpack."
        ),
        hashMapOf(
            "id" to "2",
            "name" to "Wireless Bluetooth Headphones",
            "price" to 129.50,
            "imageUrl" to "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&q=60",
            "description" to "High-fidelity wireless Bluetooth headphones."
        ),
        hashMapOf(
            "id" to "3",
            "name" to "Organic Green Tea",
            "price" to 12.00,
            "imageUrl" to "https://images.unsplash.com/photo-1576092762791-d240316e6153?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&q=60",
            "description" to "A refreshing and healthy pack of organic green tea leaves."
        )
    )

    fun addSampleProducts() {
        sampleProducts.forEach { product ->
            db.collection("products")
                .document(product["id"] as String)
                .set(product)
                .addOnSuccessListener {
                    Log.d("Firestore", "Product added: ${product["name"]}")
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error adding product", e)
                }
        }
    }


    companion object {
        lateinit var firebaseAuth: FirebaseAuth
    }
}





