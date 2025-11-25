package com.example.foodwastemangmentapplication1.HomeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items // Import for LazyRow items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home // Example Icon
import androidx.compose.material.icons.filled.List // Example Icon
import androidx.compose.material.icons.filled.Menu // Example Icon
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person // Example Icon
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar // M3 Bottom Navigation
import androidx.compose.material3.NavigationBarItem // M3 Bottom Navigation Item
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue // For remember
import androidx.compose.runtime.mutableStateOf // For remember
import androidx.compose.runtime.remember // For remember
import androidx.compose.runtime.setValue // For remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodwastemangmentapplication1.NavigationController.Screen
import com.example.foodwastemangmentapplication1.R // Assuming this is correct
import com.example.foodwastemangmentapplication1.donations.Donation
import com.example.foodwastemangmentapplication1.donations.DonationHistoryItem
import com.example.foodwastemangmentapplication1.donations.DonationHistoryScreen
import com.example.foodwastemangmentapplication1.donations.DonationHistoryViewModel

// Define your colors - It's good practice to define these in a separate Colors.kt file
// or within your Theme.kt
val PrimaryGreen = Color(0xFF4CAF50) // Example Green
val PrimaryOrange = Color(0xFFFF9800) // Example Orange

data class DonorBottomNavItem( // <--- THIS IS NOW AT THE TOP LEVEL (GOOD!)
    val label: String,
    val icon: ImageVector,
    val route: String
)

// Define a simple theme for preview if you don't have one set up
@Composable
fun FoodSaverTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy( // Or your custom color scheme
            primary = PrimaryGreen,
            secondary = PrimaryOrange
        ),
        typography = MaterialTheme.typography, // Or your custom typography
        shapes = MaterialTheme.shapes, // Or your custom shapes
        content = content
    )
}


@Composable
fun DonorHomeScreen(navController: NavController,
                    viewModel: DonationHistoryViewModel = viewModel()) {
    val donations by viewModel.donations.collectAsState()
    Scaffold(
        topBar = { DonorHomeHeader(navController) }, // Added TopBar
        bottomBar = { DonorBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background) // Use theme background

        ) {
            // Main Content Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // User Info & Badges
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Hello, Donor Dhruva!",
                            style = MaterialTheme.typography.headlineSmall, // M3 Typography
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Ready to make a difference?",
                            style = MaterialTheme.typography.bodyLarge // M3 Typography
                        )
                    }
                    // Donor Badge
                    // Ensure R.drawable.donation_badge_icon exists in your res/drawable folder
                    Image(
                        painter = painterResource(id = R.drawable.badge),
                        contentDescription = "Donor Badge",
                        modifier = Modifier.size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Stats Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    StatCard(label = "Total Donations", value = "50")
                    StatCard(label = "Meals Served", value = "250")
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Primary Action Button
                Button(
                    onClick = {
                        navController.navigate(Screen.ScreenAddDonationsScreenRoute.route)
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen) // M3 containerColor
                ) {
                    Text(
                        text = "Donate Food",
                        style = MaterialTheme.typography.labelLarge, // M3 Typography
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Upcoming Donations
                Text(
                    text = "Upcoming Donations",
                    style = MaterialTheme.typography.titleMedium, // M3 Typography
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
//
                LazyRow(
//
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(donations) { donation ->
                        UpcomingDonationCard(donation = donation)
                    }
                }
                
//
            }
        }
    }
}

@Composable
fun DonorHomeHeader(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface) // Use theme surface color for app bars
            .padding(top = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Open navigation drawer */ }) {
            Icon(
                imageVector = Icons.Default.Menu, // Example Menu Icon
                contentDescription = "Menu"
            )
        }

        Text("Food Bridge ", style = MaterialTheme.typography.titleMedium) // Placeholder for logo

        IconButton(onClick = {
            // Handle notifications click
            navController.navigate(route = Screen.ScreenCartScreenPage.route)
        }) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Notifications",

            )
        }
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // M3 elevation
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
                color = PrimaryGreen
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall // M3 Typography
            )
        }
    }
}

@Composable
fun UpcomingDonationCard(donation: Donation) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Upcoming Pickup",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Donor: ${donation.donorName}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(2.dp))
//            Text(
//                text = "Time: ${donation.scheduledTime}",
//                style = MaterialTheme.typography.bodySmall
//            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Food: ${donation.foodDetails}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun UpcomingDonationsList(viewModel: DonationHistoryViewModel = viewModel()) {
    val donations by viewModel.donations.collectAsState()

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(donations) { donation ->
            UpcomingDonationCard(donation = donation)
        }
    }
}

// Data class for Bottom Navigation items
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)



@Composable
fun DonorBottomBar(navController: NavController) {
    val items = listOf(
        DonorBottomNavItem("Home", Icons.Default.Home, Screen.ScreenHomeRoute.route),
        DonorBottomNavItem("Market Place", Icons.Default.AddCircle, Screen.ScreenProductListRoute.route),
        DonorBottomNavItem("Donations", Icons.Default.List, Screen.ScreenDonationRoute.route), // Or your specific donations list route
        DonorBottomNavItem("Profile", Icons.Default.Person, Screen.ScreenProfileRoute.route)
    )

    // Observe the current route to update the selected tab
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Example color
        modifier = Modifier.navigationBarsPadding()
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route, // Highlight based on current route
                onClick = {
                    if (currentRoute != screen.route) { // Avoid navigating to the same screen
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items.
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors( // Customize selected/unselected colors
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f) // Indicator behind selected item
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonorHomeScreenPreview() {
    FoodSaverTheme { // Use the defined theme
        DonorHomeScreen(navController = rememberNavController())
    }
}
