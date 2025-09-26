//package com.example.foodwastemangmentapplication1.HomeScreen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//
//// This data class will hold the information for each donation item
//data class Donation(
//    val date: String,
//    val items: String,
//    val quantity: String,
//    val unit: String
//)
//
//@Composable
//fun DonationHistoryScreen(navController: NavController) {
//    // Sample data for the list
//    val donations = listOf(
//        Donation("October 26, 2023", "Canned Goods, Fresh Produce", "10", "lbs"),
//        Donation("October 26, 2023", "Canned Goods", "12", "cans"),
//        Donation("October 26, 2023", "Bread Loaves", "3", "loaves"),
//        Donation("October 26, 2023", "Mixed Vegetables", "5", "kg"),
//        Donation("October 26, 2023", "Canned Soup", "8", "cans"),
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        // Top Bar
//        TopAppBar(
//            title = {
//                Text(
//                    text = "Donation History",
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold
//                )
//            },
//            backgroundColor = PrimaryGreen,
//            contentColor = Color.White,
//            navigationIcon = {
//                IconButton(onClick = { navController.popBackStack() }) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowBack,
//                        contentDescription = "Back"
//                    )
//                }
//            }
//        )
//
//        // Donation List
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f) // Fills the available space
//                .padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(donations) { donation ->
//                DonationHistoryItem(donation = donation)
//            }
//        }
//
//        // Action Button
//        Button(
//            onClick = {
//                // Navigate to the food donation listing screen
//                navController.navigate("add_donation")
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 24.dp)
//                .height(50.dp),
//            shape = RoundedCornerShape(12.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryOrange)
//        ) {
//            Text(
//                text = "Make a New Donation",
//                color = Color.White,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}
//
//@Composable
//fun DonationHistoryItem(donation: Donation) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = 4.dp,
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = donation.date,
//                    style = MaterialTheme.typography.body1,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = donation.items,
//                    style = MaterialTheme.typography.body2,
//                    color = Color.Gray
//                )
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(
//                horizontalAlignment = Alignment.End
//            ) {
//                Text(
//                    text = "${donation.quantity} ${donation.unit}",
//                    style = MaterialTheme.typography.subtitle1,
//                    color = PrimaryGreen,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DonationHistoryScreenPreview() {
//    FoodSaverTheme {
//        DonationHistoryScreen(navController = rememberNavController())
//    }
//}

package com.example.foodwastemangmentapplication1.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
// Remove M2 imports if not used elsewhere explicitly by you
// import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults // Added for M3 Card elevation
import androidx.compose.material3.ExperimentalMaterial3Api // Needed for M3 TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar // Using M3 TopAppBar
import androidx.compose.material3.TopAppBarDefaults // Added for M3 TopAppBar colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodwastemangmentapplication1.NavigationController.Screen

// Assuming these are defined elsewhere (e.g., in your Theme or a Colors.kt file)
// If not, you'll need to define them for the preview and runtime:
// val PrimaryGreen = Color(0xFF4CAF50)
// val PrimaryOrange = Color(0xFFFF9800)

// Assuming FoodSaverTheme is defined elsewhere (e.g., in your ui.theme package)
// If not, for preview purposes, you might need a local definition:
/*
@Composable
fun FoodSaverTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = PrimaryGreen,
            secondary = PrimaryOrange
        ),
        content = content
    )
}
*/


// This data class will hold the information for each donation item
data class Donation(
    val id: String = java.util.UUID.randomUUID().toString(), // Added a unique key for LazyColumn items
    val date: String,
    val items: String,
    val quantity: String,
   // val unit: String
)

@OptIn(ExperimentalMaterial3Api::class) // Required for Material3 TopAppBar
@Composable
fun DonationHistoryScreen(navController: NavController) {
    // Sample data for the list
    val donations = listOf(
        Donation("October 26, 2023", "Canned Goods, Fresh Produce", "10", "lbs"),
        Donation("October 27, 2023", "Canned Goods", "12", "cans"), // Changed date for unique keys
        Donation("October 28, 2023", "Bread Loaves", "3", "loaves"),  // Changed date for unique keys
        Donation("October 29, 2023", "Mixed Vegetables", "5", "kg"),  // Changed date for unique keys
        Donation("October 30, 2023", "Canned Soup", "8", "cans"),   // Changed date for unique keys
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Use theme background color
    ) {
        // Top Bar - Material 3
        TopAppBar(
            title = {
                Text(
                    text = "Donation History",
                    // color = Color.White, // Handled by TopAppBarDefaults
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = PrimaryGreen, // Defined in your theme or Colors.kt
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(Screen.ScreenHomeRoute.route) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        // Donation List
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Fills the available space
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(donations, key = { donation -> donation.id }) { donation -> // Added key for performance
                DonationHistoryItem(donation = donation)
            }
        }

        // Action Button - Material 3
        Button(
            onClick = {
                // Navigate to the food donation listing screen
                navController.navigate("add_donation") // Ensure this route exists
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange) // M3 containerColor
        ) {
            Text(
                text = "Make a New Donation",
                color = Color.White, // For Buttons, text color is often inferred or can be set directly
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge // Example M3 style
            )
        }
    }
}

@Composable
fun DonationHistoryItem(donation: Donation) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // M3 elevation
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant) // Example M3 card color
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = donation.date,
                    style = MaterialTheme.typography.bodyLarge, // M3 Typography
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = donation.items,
                    style = MaterialTheme.typography.bodyMedium, // M3 Typography
                    color = MaterialTheme.colorScheme.onSurfaceVariant // Use theme color
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${donation.quantity} {donation.unit}",
                    style = MaterialTheme.typography.titleMedium, // M3 Typography
                    color = PrimaryGreen, // Or use MaterialTheme.colorScheme.primary
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonationHistoryScreenPreview() {
    // Make sure FoodSaverTheme, PrimaryGreen, and PrimaryOrange are accessible here
    // For the preview to work correctly, you might need to define them if they aren't globally available.
    // Example:
    val PrimaryGreen = Color(0xFF4CAF50)
    val PrimaryOrange = Color(0xFFFF9800)

    MaterialTheme( // Using a basic MaterialTheme for preview if FoodSaverTheme isn't set up
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = PrimaryGreen,
            secondary = PrimaryOrange,
            surfaceVariant = Color.LightGray // for card background example
        )
    ) {
        DonationHistoryScreen(navController = rememberNavController())
    }
}
