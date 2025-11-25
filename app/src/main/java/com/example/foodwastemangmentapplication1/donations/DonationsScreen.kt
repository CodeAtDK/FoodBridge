package com.example.foodwastemangmentapplication1.donations

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodwastemangmentapplication1.HomeScreen.PrimaryGreen
import com.example.foodwastemangmentapplication1.HomeScreen.PrimaryOrange
import com.example.foodwastemangmentapplication1.NavigationController.Screen
import java.util.UUID



@OptIn(ExperimentalMaterial3Api::class) // Required for Material3 TopAppBar
@Composable
fun DonationHistoryScreen(navController: NavController, viewModel: DonationHistoryViewModel = viewModel()) {

    val donations by viewModel.donations.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Donation History",
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = PrimaryGreen,
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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(donations) { donation ->
                DonationHistoryItem(donation = donation)
            }
        }

        Button(
            onClick = {
                navController.navigate(Screen.ScreenAddDonationsScreenRoute.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
        ) {
            Text(
                text = "Make a New Donation",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun DonationHistoryItem(donation: Donation) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
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
                    text = donation.donorName, // Using donorName since no date field now
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = donation.foodDetails,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Contact: ${donation.contact}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Packaged: ${if (donation.packaged) "Yes" else "No"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Location: ${donation.latitude ?: "N/A"}, ${donation.longitude ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DonationHistoryScreenPreview() {

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
