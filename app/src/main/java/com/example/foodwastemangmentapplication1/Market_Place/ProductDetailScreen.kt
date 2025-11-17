//package com.example.foodwastemangmentapplication1.Market_Place
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Button
//import androidx.compose.material3.Divider
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import coil.compose.rememberImagePainter
//
//@Composable
//fun ProductDetailScreen(product: Product, onAddToCart: (Product) -> Unit) {
//    Scaffold(
//        bottomBar = {
//            Button(
//                onClick = { onAddToCart(product) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .height(50.dp)
//            ) {
//                Text("Add to Cart - $${product.price}")
//            }
//        }
//    ) { paddingValues ->
//        LazyColumn(contentPadding = paddingValues) {
//            item {
//                // Product Image Section (Simplified)
//                Image(
//                    painter = rememberImagePainter(product.imageUrl),
//                    contentDescription = product.name,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(300.dp),
//                    contentScale = ContentScale.Crop
//                )
//
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text(text = product.name, style = MaterialTheme.typography.h4)
//                    Spacer(Modifier.height(8.dp))
//                    Text(text = "$${product.price}", style = MaterialTheme.typography.h5, color = MaterialTheme.colors.secondary)
//                    Divider(Modifier.padding(vertical = 16.dp))
//                    Text(text = "Description", style = MaterialTheme.typography.h6)
//                    Text(text = product.description, style = MaterialTheme.typography.body1)
//
//                    // Add more details like size, color, seller info, etc. here
//                }
//            }
//        }
//    }
//}

package com.example.foodwastemangmentapplication1.Market_Place

import androidx.compose.foundation.layout.Arrangement // For Column spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues // For LazyColumn contentPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons // For TopAppBar
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage // Modern Coil Composable
import androidx.navigation.NavController // For TopAppBar navigation
import androidx.navigation.compose.rememberNavController // For preview
import com.example.foodwastemangmentapplication1.NavigationController.Screen

// Assume Product data class is defined like this:



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavController, // Added for TopAppBar back navigation
    product: Product,
    onAddToCart: (Product) -> Unit
) {
    //val product by remember { mutableStateOf(viewModel.products) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product.id) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface, // Or primaryContainer
                    titleContentColor = MaterialTheme.colorScheme.onSurface // Or onPrimaryContainer
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { //onAddToCart(product)
                     navController.navigate(Screen.ScreenAddresSelectionRoute.route)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding for the button within the bottom bar
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    "Add to Cart - $${String.format("%.2f", product.price)}", // Format price
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        // Option 1: LazyColumn (good if content becomes very long or has multiple distinct sections)
        LazyColumn(
            modifier = Modifier.padding(paddingValues), // Apply padding from Scaffold
            contentPadding = PaddingValues(bottom = 16.dp) // Extra padding at the bottom if needed
        ) {
            item {
                ProductImageSection(imageUrl = product.imageUrl, contentDescription = product.name)
            }
            item {
                ProductInfoSection(product = product)
            }
            // Add more items for reviews, related products etc.
            // item { RelatedProductsSection() }
        }

        // Option 2: Column with verticalScroll (simpler if content is moderately sized)
        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from Scaffold
                .verticalScroll(rememberScrollState())
        ) {
            ProductImageSection(imageUrl = product.imageUrl, contentDescription = product.name)
            ProductInfoSection(product = product)
            // Add more details like size, color, seller info, etc. here
            Spacer(Modifier.height(16.dp)) // Ensure content doesn't hide behind bottom bar
        }
        */
    }
}

@Composable
fun ProductImageSection(imageUrl: String, contentDescription: String?) {
    AsyncImage( // Using modern Coil AsyncImage
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Crop,
        // placeholder = painterResource(R.drawable.placeholder_image), // Optional placeholder
        // error = painterResource(R.drawable.error_image) // Optional error image
    )
}

@Composable
fun ProductInfoSection(product: Product) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Consistent spacing
    ) {
        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineMedium // M3 Typography
        )
        Text(
            text = "$${String.format("%.2f", product.price)}", // Format price
            style = MaterialTheme.typography.titleLarge, // M3 Typography
            color = MaterialTheme.colorScheme.primary // Use primary color for price
        )
//        Text(
//            text = "Category: ${product.category}",
//            style = MaterialTheme.typography.labelMedium,
//            color = MaterialTheme.colorScheme.onSurfaceVariant
//        )
        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant // M3 Divider color
        )
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium // M3 Typography
        )
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge // M3 Typography
        )
        // Add more details like size, color, seller info, etc. here
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
