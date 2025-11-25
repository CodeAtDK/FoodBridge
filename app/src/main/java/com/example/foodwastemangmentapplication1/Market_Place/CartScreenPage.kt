package com.example.foodwastemangmentapplication1.Market_Place

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.foodwastemangmentapplication1.NavigationController.Screen
import okhttp3.Route

@Composable
fun AddToCartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel()
) {
    val cartItems = viewModel.cartItems
    val totalPrice = viewModel.totalPrice

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header Icon
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Cart Icon",
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFF4CAF50), CircleShape)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (cartItems.isEmpty()) {
            Text("Your cart is empty.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cartItems, key = { it.id }) { product ->
                    CartItem(product = product, onRemove = {
                        viewModel.removeFromCart(product.id)
                    })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Total Price
            Text(
                text = "Total: $${String.format("%.2f", totalPrice)}",
                style = MaterialTheme.typography.titleLarge.copy(
                    textDecoration = TextDecoration.LineThrough // adds the cross
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Total: after discount $${String.format("%.2f", totalPrice*0.9)}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buy Now Button
            Button(
                onClick = {
//                    viewModel.buyNow {
//                        navController.navigate("order_confirmation")
//                    }
                    navController.navigate(route = Screen.ScreenAddresSelectionRoute.route)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Buy Now", color = Color.White)
            }
        }
    }
}

@Composable
fun CartItem(product: Product, onRemove: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(product.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    "$${String.format("%.2f", product.price)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = "Remove")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddToCartScreenPreview() {
    val sampleProducts = listOf(
        Product(
            id = "1",
            name = "Fresh Apples",
            price = 3.50,
            imageUrl = "https://via.placeholder.com/150",
            description = "Crisp and sweet apples."
        ),
        Product(
            id = "2",
            name = "Organic Bread",
            price = 2.25,
            imageUrl = "https://via.placeholder.com/150",
            description = "Whole grain bread baked fresh."
        ),
        Product(
            id = "3",
            name = "Milk 1L",
            price = 1.75,
            imageUrl = "https://via.placeholder.com/150",
            description = "Farm fresh milk."
        )
    )

    // Fake ViewModel for preview
    val fakeViewModel = object : CartViewModel() {
        init {
            cartItems = sampleProducts
            totalPrice = sampleProducts.sumOf { it.price }
        }
    }

    // Dummy NavController
    val navController = rememberNavController()

    MaterialTheme {
        AddToCartScreen(
            navController = navController,
            viewModel = fakeViewModel
        )
    }
}