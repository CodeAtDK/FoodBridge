//package com.example.foodwastemangmentapplication1.HomeScreen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//
//// Assume you have these resources and colors defined
//// R.drawable.profile_placeholder
//// R.drawable.donation_badge_icon
//// PrimaryGreen, PrimaryOrange
//
//@Composable
//fun DonorProfileScreen(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        // Top Bar
//        TopAppBar(
//            title = {
//                Text(
//                    text = "Profile",
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
//        // Main Content Area
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Profile Image
//            Image(
//                painter = painterResource(id = ),
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(CircleShape)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // User Name and Donor Type
//            Text(
//                text = "Dhruva Sharma",
//                style = MaterialTheme.typography.h5,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = "Donor Type: Restaurant",
//                style = MaterialTheme.typography.body1,
//                color = Color.Gray
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Stats Section (reusable from Home Screen)
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceAround
//            ) {
//                StatCard(label = "Total Donations", value = "15")
//                StatCard(label = "Meals Served", value = "250")
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Donor Badge
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.donation_badge_icon),
//                    contentDescription = "Donor Badge",
//                    modifier = Modifier.size(40.dp)
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Text(
//                    text = "Bronze Donor",
//                    style = MaterialTheme.typography.subtitle1,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            // Profile Management Options
//            ProfileOptionItem(
//                text = "Edit Profile",
//                onClick = { /* Navigate to Edit Profile screen */ }
//            )
//            ProfileOptionItem(
//                text = "Privacy Settings",
//                onClick = { /* Navigate to Privacy Settings screen */ }
//            )
//            ProfileOptionItem(
//                text = "Contact Support",
//                onClick = { /* Navigate to Contact Support screen */ }
//            )
//        }
//    }
//}
//
//@Composable
//fun ProfileOptionItem(text: String, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(60.dp)
//            .padding(vertical = 4.dp),
//        shape = RoundedCornerShape(12.dp),
//        elevation = 2.dp
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = text,
//                style = MaterialTheme.typography.body1,
//                fontWeight = FontWeight.Medium
//            )
//            IconButton(onClick = onClick) {
//                Icon(
//                    imageVector = Icons.Default.ChevronRight,
//                    contentDescription = null,
//                    tint = Color.Gray
//                )
//            }
//        }
//    }
//}
//
//// Reusing StatCard from the HomeScreen code
//@Composable
//fun StatCard(label: String, value: String) {
//    Card(
//        modifier = Modifier
//            .width(150.dp)
//            .height(80.dp),
//        shape = RoundedCornerShape(12.dp),
//        elevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(12.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = value,
//                style = MaterialTheme.typography.h4,
//                fontWeight = FontWeight.Bold,
//                color = PrimaryGreen
//            )
//            Text(
//                text = label,
//                style = MaterialTheme.typography.caption
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DonorProfileScreenPreview() {
//    FoodSaverTheme {
//        DonorProfileScreen(navController = rememberNavController())
//    }
//}
package com.example.foodwastemangmentapplication1.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
// import androidx.compose.material.* // Comment out M2 imports if not needed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle // Placeholder for profile
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star // Alternative placeholder for badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar // M3 TopAppBar
import androidx.compose.material3.TopAppBarDefaults // For M3 TopAppBar colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
// import androidx.compose.ui.res.painterResource // Keep if you use actual drawables
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodwastemangmentapplication1.NavigationController.Screen

// Define colors and theme here for preview if not globally available
// val PrimaryGreen = Color(0xFF4CAF50) // Example Green
// val PrimaryOrange = Color(0xFFFF9800) // Example Orange

/*
@Composable
fun FoodSaverTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = PrimaryGreen,
            secondary = PrimaryOrange,
            surfaceVariant = Color.LightGray,
            onSurfaceVariant = Color.DarkGray
        ),
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonorProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Use theme background
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Text(
                    text = "Profile",
                    // color = Color.White, // Handled by TopAppBarDefaults
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = PrimaryGreen, // Make sure PrimaryGreen is accessible
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

        // Main Content Area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image (using default icon as placeholder)
            Icon(
                imageVector = Icons.Filled.AccountCircle, // Placeholder
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant), // Placeholder background
                tint = MaterialTheme.colorScheme.primary
            )
            // If you had a painter resource:
            // Image(
            // painter = painterResource(id = R.drawable.profile_placeholder),
            // contentDescription = "Profile Picture",
            // modifier = Modifier
            // .size(100.dp)
            // .clip(CircleShape)
            // )

            Spacer(modifier = Modifier.height(16.dp))

            // User Name and Donor Type
            Text(
                text = "Dhruva Khatavkar", // Replace with actual data
                style = MaterialTheme.typography.headlineSmall, // M3 Typography
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Donor Type: Restaurant", // Replace with actual data
                style = MaterialTheme.typography.bodyLarge, // M3 Typography
                color = MaterialTheme.colorScheme.onSurfaceVariant // Use theme color
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Stats Section (reusable from Home Screen)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
//                StatCard(label = "Total Donations", value = "15")
//                StatCard(label = "Meals Served", value = "250")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Donor Badge
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star, // Placeholder for badge icon (e.g., Star, WorkspacePremium)
                    contentDescription = "Donor Badge",
                    modifier = Modifier.size(40.dp),
                    tint = PrimaryOrange // Make sure PrimaryOrange is accessible
                )
                // If you had a painter resource:
                // Image(
                // painter = painterResource(id = R.drawable.donation_badge_icon),
                // contentDescription = "Donor Badge",
                // modifier = Modifier.size(40.dp)
                // )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Bronze Donor", // Replace with actual data
                    style = MaterialTheme.typography.titleMedium, // M3 Typography
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Profile Management Options
            ProfileOptionItem(
                text = "Edit Profile",
                onClick = { /* Navigate to Edit Profile screen */ }
            )
            ProfileOptionItem(
                text = "Privacy Settings",
                onClick = { /* Navigate to Privacy Settings screen */ }
            )
            ProfileOptionItem(
                text = "Contact Support",
                onClick = { /* Navigate to Contact Support screen */ }
            )
        }
    }
}

@Composable
fun ProfileOptionItem(text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // M3 elevation
        onClick = onClick // Make the whole card clickable
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge, // M3 Typography
                fontWeight = FontWeight.Medium // Material 3 uses Medium, Bold, Regular etc.
            )
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null, // Decorative icon
                tint = MaterialTheme.colorScheme.onSurfaceVariant // Use theme color
            )
        }
    }
}

// Reusing StatCard from the HomeScreen code - M3 adjustments
@Composable
fun StatCard1(label: String, value: String) {
    // Ensure this is the same or imported if defined elsewhere
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // M3 elevation
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium, // M3 Typography
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen // Make sure PrimaryGreen is accessible
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall // M3 Typography
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonorProfileScreenPreview() {
    // Define these for the preview to work if they are not globally accessible
    val PrimaryGreen = Color(0xFF4CAF50)
    val PrimaryOrange = Color(0xFFFF9800)

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = PrimaryGreen,
            secondary = PrimaryOrange,
            surfaceVariant = Color(0xFFE0E0E0), // A light gray for card backgrounds
            onSurfaceVariant = Color.Black,
            background = Color.White
        )
    ) { // Replace with your actual FoodSaverTheme
        DonorProfileScreen(navController = rememberNavController())
    }
}
