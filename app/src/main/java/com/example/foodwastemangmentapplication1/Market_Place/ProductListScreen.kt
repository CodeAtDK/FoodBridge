//package com.example.foodwastemangmentapplication1.Market_Place
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import coil.compose.rememberImagePainter
//
//@Composable
//fun ProductListScreen(onProductClick: (Product) -> Unit) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Marketplace") },
//                actions = {
//                    IconButton(onClick = { /* Handle search */ }) {
//                        Icon(Icons.Filled.Search, contentDescription = "Search")
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//        LazyColumn(contentPadding = paddingValues) {
//            // Replace 'sampleProducts' with your actual data source
//            items(sampleProducts) { product ->
//                ProductListItem(product = product, onClick = onProductClick)
//            }
//        }
//    }
//}
//
//@Composable
//fun ProductListItem(product: Product, onClick: (Product) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp, vertical = 4.dp)
//            .clickable { onClick(product) },
//        elevation = 4.dp
//    ) {
//        Row(modifier = Modifier.padding(12.dp)) {
//            // Placeholder for product image
//            Image(
//                painter = rememberImagePainter(product.imageUrl),
//                contentDescription = product.name,
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(RoundedCornerShape(4.dp)),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(Modifier.width(16.dp))
//            Column {
//                Text(text = product.name, style = MaterialTheme.typography.subtitle1, maxLines = 1)
//                Spacer(Modifier.height(4.dp))
//                Text(text = "$${product.price}", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.primary)
//                // Add Rating Bar here if needed
//            }
//        }
//    }
//}
//// Data classes for demonstration
//data class Product(val id: Int, val name: String, val price: Double, val imageUrl: String, val description: String)
//val sampleProducts = listOf(
//    Product(1, "Vintage Camera", 199.99, "url1", "A classic camera."),
//    Product(2, "Leather Wallet", 45.00, "url2", "Genuine leather.")
//)

package com.example.foodwastemangmentapplication1.Market_Place

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement // For Column spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues // For LazyColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Correct import
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage // Modern Coil Composable

// Consistent Product data class (assuming from ProductDetailScreen)
// data class Product(
// val id: String,
// val name: String,
// val description: String,
// val price: Double,
// val imageUrl: String,
//    val category: String = "Unknown"
// )

// Sample products with valid placeholder image URLs (replace with your actual image URLs)
//val sampleProductsList = listOf(
//    Product(
//        id = "1",
//        name = "Vintage Leather Backpack",
//        price = 79.99,
//        imageUrl = "https://images.unsplash.com/photo-1553062407-98eeb68c6a62?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8YmFja3BhY2t8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=400&q=60",
//        description = "A stylish and durable vintage leather backpack, perfect for everyday use or travel."
//    ),
//    Product(
//        id = "2",
//        name = "Wireless Bluetooth Headphones",
//        price = 129.50,
//        imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8aGVhZHBob25lc3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=400&q=60",
//        description = "High-fidelity wireless Bluetooth headphones with noise-cancellation and long battery life."
//    ),
//    Product(
//        id = "3",
//        name = "Organic Green Tea",
//        price = 12.00,
//        imageUrl = "https://images.unsplash.com/photo-1576092762791-d240316e6153?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Z3JlZW4lMjB0ZWF8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=400&q=60",
//        description = "A refreshing and healthy pack of organic green tea leaves, sourced sustainably."
//    )
//)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = viewModel(), // Pass the list of products
    onProductClick: (Product) -> Unit,
    onSearchClick: () -> Unit // Callback for search action
) {
    val products by remember { mutableStateOf(viewModel.products) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Marketplace") },
                actions = {
                    IconButton(onClick = onSearchClick) { // Use callback
                        Icon(Icons.Filled.Search, contentDescription = "Search Products")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        if (products.isEmpty()) {
            // Optional: Show a message if there are no products
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No products found.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 8.dp, // Add to scaffold padding
                    bottom = paddingValues.calculateBottomPadding() + 8.dp, // Add to scaffold padding
                    start = 8.dp,
                    end = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp) // Space between items
            ) {
                items(products, key = { product -> product.id }) { product -> // Added key
                    ProductListItem(product = product, onClick = onProductClick)
                }
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            // .padding(horizontal = 8.dp, vertical = 4.dp) // Padding applied by LazyColumn's contentPadding & Arrangement
            .clickable { onClick(product) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // M3 elevation
        shape = RoundedCornerShape(8.dp), // Slightly more rounded corners
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage( // Using modern Coil AsyncImage
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)), // Match card corner for image
                contentScale = ContentScale.Crop,
                // placeholder = painterResource(R.drawable.placeholder_image), // Optional
                // error = painterResource(R.drawable.error_image) // Optional
            )
            Spacer(Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f), // Allow column to take available space
                verticalArrangement = Arrangement.spacedBy(4.dp) // Spacing in column
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium, // M3 Typography
                    maxLines = 2, // Allow for slightly longer names
                    overflow = TextOverflow.Ellipsis
                )
                // You could add a short description here if needed
                // Text(
                // text = product.description.take(50) + "...", // Example short description
                // style = MaterialTheme.typography.bodySmall,
                // maxLines = 1,
                // overflow = TextOverflow.Ellipsis
                // )
                Text(
                    text = "$${String.format("%.2f", product.price)}", // Format price
                    style = MaterialTheme.typography.bodyLarge, // M3 Typography
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
                // Add Rating Bar here if needed
                // Example: RatingBar(rating = product.rating, ...)
            }
        }
    }
}

// Data class for Product (ensure this is the one you are using across your app)
// data class Product(
// val id: String,
// val name: String,
//    val price: Double,
//    val imageUrl: String,
//    val description: String,
//    val category: String // Example additional field
// )

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    MaterialTheme {
        ProductListScreen(
            navController = rememberNavController(),
            //products = sampleProductsList, // Use the updated sample list
            onProductClick = { product -> println("Clicked on ${product.name}") },
            onSearchClick = { println("Search clicked") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenEmptyPreview() {
    MaterialTheme {
        ProductListScreen(

            navController = rememberNavController(),
          //  products = emptyList(), // Test empty state
            onProductClick = { product -> println("Clicked on ${product.name}") },
            onSearchClick = { println("Search clicked") }
        )
    }
}
