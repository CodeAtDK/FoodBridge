//package com.example.foodwastemangmentapplication1.HomeScreen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
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
//import com.example.foodwastemangmentapplication1.R
//
//
//// Assume you have these resources and colors defined in your project
//// R.drawable.profile_placeholder
//// R.drawable.donation_badge_icon
//// PrimaryGreen, PrimaryOrange colors
//
//@Composable
//fun DonorHomeScreen(navController: NavController) {
//    Scaffold(
//        bottomBar = { //DonorBottomBar(navController)
//             }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .background(Color.White)
//        ) {
//            // Header
//           // DonorHomeHeader()
//
//            // Main Content Area
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // User Info & Badges
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text(
//                            text = "Hello, Donor!",
//                            style = MaterialTheme.typography.h5,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Text(
//                            text = "Ready to make a difference?",
//                            style = MaterialTheme.typography.body1
//                        )
//                    }
//                    // Donor Badge
//                    Image(
//                        painter = painterResource(id = R.drawable.donation_badge_icon),
//                        contentDescription = "Donor Badge",
//                        modifier = Modifier.size(50.dp)
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(32.dp))
//
//                // Stats Section
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceAround
//                ) {
//                    //StatCard(label = "Total Donations", value = "50")
//                    //StatCard(label = "Meals Served", value = "250")
//                }
//
//                Spacer(modifier = Modifier.height(48.dp))
//
//                // Primary Action Button
//                Button(
//                    onClick = {
//                        // Navigate to the food donation listing screen
//                        navController.navigate("add_donation")
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(60.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryGreen)
//                ) {
//                    Text(
//                        text = "Donate Food",
//                        style = MaterialTheme.typography.button,
//                        color = Color.White
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Upcoming Donations
//                Text(
//                    text = "Upcoming Donations",
//                    style = MaterialTheme.typography.h6,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                LazyRow(
//                    horizontalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    items(3) {
//                        UpcomingDonationCard()
//                    }
//                }
//            }
//        }
//        )
//    }
//
//    @Composable
//    fun DonorHomeHeader() {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = { /* Open navigation drawer */ }) {
//                Icon(
//                    //painter = painterResource(id = R.drawable.ic_menu), // Assume you have a menu icon
//                    contentDescription = "Menu"
//                )
//            }
//            Image(
//               // painter = painterResource(id = R.drawable.app_logo),
//                contentDescription = "App Logo",
//                modifier = Modifier.size(40.dp)
//            )
//            IconButton(onClick = {
//                // Handle notifications click
//            }) {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Notifications"
//                )
//            }
//        }
//    }
//
//    @Composable
//    fun StatCard(label: String, value: String) {
//        Card(
//            modifier = Modifier
//                .width(150.dp)
//                .height(80.dp),
//            shape = RoundedCornerShape(12.dp),
//            elevation = 4.dp
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(12.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = value,
//                    style = MaterialTheme.typography.h4,
//                    fontWeight = FontWeight.Bold,
//                    color = PrimaryGreen
//                )
//                Text(
//                    text = label,
//                    style = MaterialTheme.typography.caption
//                )
//            }
//        }
//    }
//
//    @Composable
//    fun UpcomingDonationCard() {
//        Card(
//            modifier = Modifier
//                .width(200.dp)
//                .height(100.dp),
//            shape = RoundedCornerShape(12.dp),
//            elevation = 4.dp
//        ) {
//            Column(
//                modifier = Modifier.padding(12.dp)
//            ) {
//                Text(
//                    text = "Upcoming Pickup",
//                    style = MaterialTheme.typography.body1,
//                    fontWeight = FontWeight.Bold
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Scheduled for tomorrow at 2:00 PM",
//                    style = MaterialTheme.typography.caption
//                )
//            }
//        }
//    }
//
//    @Composable
//    fun DonorBottomBar(navController: NavController) {
//        BottomNavigation(
//            backgroundColor = Color.White,
//            modifier = Modifier.height(60.dp)
//        ) {
//            // You would typically use a sealed class for your nav destinations
//            val items = listOf("Home", "Donations", "Profile")
//            items.forEach { screen ->
//                BottomNavigationItem(
//                    icon = { /* Your icon Composable */ },
//                    label = { Text(screen) },
//                    selected = screen == "Home", // Change based on current destination
//                    onClick = {
//                        // navController.navigate(screen)
//                    }
//                )
//            }
//        }
//    }
//
//    @Preview(showBackground = true)
//    @Composable
//    fun DonorHomeScreenPreview() {
//        FoodSaverTheme {
//            DonorHomeScreen(navController = rememberNavController())
//        }
//    }

package com.example.foodwastemangmentapplication1.HomeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items // Import for LazyRow items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home // Example Icon
import androidx.compose.material.icons.filled.List // Example Icon
import androidx.compose.material.icons.filled.Menu // Example Icon
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person // Example Icon
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
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodwastemangmentapplication1.NavigationController.Screen
import com.example.foodwastemangmentapplication1.R // Assuming this is correct

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
fun DonorHomeScreen(navController: NavController) {
    Scaffold(
        topBar = { DonorHomeHeader() }, // Added TopBar
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
                            text = "Hello, Donor!",
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
                        painter = painterResource(id = R.drawable.ic_launcher_background),
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

//                        val context = LocalContext.current
//                        Toast.makeText(context, "Coming Soon...", Toast.LENGTH_LONG).show()

                        // Navigate to the food donation listing screen
                       // navController.navigate("add_donation") // Ensure this route exists
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
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) { // Use the imported items
                        UpcomingDonationCard()
                    }
                }
                
                Button(
                    onClick = { navController.navigate(Screen.ScreenProductListRoute.route)}
                ) {
                    Text(
                        text = "Market Place Demo",
                        style = MaterialTheme.typography.labelLarge, // M3 Typography
                        color = Color.White
                    )
                }
            }
        }
    }
} // DonorHomeScreen Composable ends here

@Composable
fun DonorHomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface) // Use theme surface color for app bars
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Open navigation drawer */ }) {
            Icon(
                imageVector = Icons.Default.Menu, // Example Menu Icon
                contentDescription = "Menu"
            )
        }
        // Replace with your actual app logo if you have one
        // Image(
        // painter = painterResource(id = R.drawable.app_logo),
        // contentDescription = "App Logo",
        // modifier = Modifier.size(40.dp)
        // )
        Text("Food Bridge ", style = MaterialTheme.typography.titleMedium) // Placeholder for logo

        IconButton(onClick = {
            // Handle notifications click
        }) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications"
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
fun UpcomingDonationCard() {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // M3 elevation
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Upcoming Pickup",
                style = MaterialTheme.typography.titleSmall, // M3 Typography
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Scheduled for tomorrow at 2:00 PM",
                style = MaterialTheme.typography.bodySmall // M3 Typography
            )
        }
    }
}

// Data class for Bottom Navigation items
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

//@Composable
//fun DonorBottomBar(navController: NavController) {
//    val items = listOf(
//        BottomNavItem("Home", Icons.Default.Home, "home_screen_route"), // Replace with actual routes
//        BottomNavItem("Donations", Icons.Default.List, "donations_screen_route"),
//        BottomNavItem("Profile", Icons.Default.Person, "profile_screen_route")
//    )
//    var selectedItemIndex by remember { mutableStateOf(0) } // Example: Home is selected initially
//
//    NavigationBar(
//        containerColor = MaterialTheme.colorScheme.surface, // M3 container color
//        modifier = Modifier.height(70.dp) // Adjusted height for better touch targets
//    ) {
//        items.forEachIndexed { index, item ->
//            NavigationBarItem(
//                selected = selectedItemIndex == index,
//                onClick = {
//
//                    navController.navigate(Screen.ScreenHomeRoute.route)
//                    selectedItemIndex = index
//                    // navController.navigate(item.route) {
//                    //    // Pop up to the start destination of the graph to
//                    //    // avoid building up a large stack of destinations
//                    //    // on the back stack as users select items
//                    //    popUpTo(navController.graph.startDestinationId) {
//                    //        saveState = true
//                    //    }
//                    //    // Avoid multiple copies of the same destination when
//                    //    // reselecting the same item
//                    //    launchSingleTop = true
//                    //    // Restore state when reselecting a previously selected item
//                    //    restoreState = true
//                    // }
//                },
//                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
//                label = { Text(item.label) },
//               // onClick = { navController.navigate(Screen.ScreenHomeRoute.route) }
//                // You can customize colors using NavigationBarItemDefaults.colors()
//            )
//        }
//    }
//}

@Composable
fun DonorBottomBar(navController: NavController) {
    val items = listOf(
        DonorBottomNavItem("Home", Icons.Default.Home, Screen.ScreenHomeRoute.route),
        DonorBottomNavItem("Donations", Icons.Default.List, Screen.ScreenDonationRoute.route), // Or your specific donations list route
        DonorBottomNavItem("Profile", Icons.Default.Person, Screen.ScreenProfileRoute.route)
    )

    // Observe the current route to update the selected tab
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Example color
        modifier = Modifier.height(70.dp)
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
