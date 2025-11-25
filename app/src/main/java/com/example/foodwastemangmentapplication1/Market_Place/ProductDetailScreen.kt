package com.example.foodwastemangmentapplication1.Market_Place

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement // For Column spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues // For LazyColumn contentPadding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons // For TopAppBar
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack // For TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api // For TopAppBar
import androidx.compose.material3.Icon // For TopAppBar
import androidx.compose.material3.IconButton // For TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar // For TopAppBar
import androidx.compose.material3.TopAppBarDefaults // For TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage // Modern Coil Composable
import androidx.navigation.NavController // For TopAppBar navigation
import androidx.navigation.compose.rememberNavController // For preview
import com.example.foodwastemangmentapplication1.NavigationController.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    product: Product,
    onAddToCart: (Product) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header Icon (like LoginScreen)
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Product Icon",
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFF4CAF50), CircleShape)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = product.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Product Image
        ProductImageSection(imageUrl = product.imageUrl, contentDescription = product.name)

        Spacer(modifier = Modifier.height(16.dp))

        // Product Info
        ProductInfoSection(product = product)

        Spacer(modifier = Modifier.height(24.dp))

        // Add to Cart Button
        Button(
            onClick = {

                addProductToCart(product, navController, context)

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text(
                "Add to Cart - $${String.format("%.2f", product.price)}",
                color = Color.White
            )
        }
    }
}

@Composable
fun ProductImageSection(imageUrl: String, contentDescription: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProductInfoSection(product: Product) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$${String.format("%.2f", product.price)}",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

fun addProductToCart(product: Product, navController: NavController, context: Context) {
    val auth = FirebaseAuth.getInstance()
    val uid = auth.currentUser?.uid ?: return

    val db = FirebaseFirestore.getInstance()
    val cartRef = db.collection("carts").document(uid).collection("items")

    cartRef.document(product.id).set(product)
        .addOnSuccessListener {
            Toast.makeText(context, "${product.name} added to cart!", Toast.LENGTH_SHORT).show()
            // Navigate if needed
           // navController.navigate(Screen.ScreenAddresSelectionRoute.route)
        }
        .addOnFailureListener { e ->
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("ProductDetailScreen", "Failed to add to cart", e)
        }
}


@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    MaterialTheme {
//        ProductDetailScreen(
//            navController = rememberNavController(),
//            product = sampleProduct,
//            onAddToCart = { println("Add to cart: ${it.name}") }
//        )
    }
}
