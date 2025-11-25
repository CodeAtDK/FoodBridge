package com.example.foodwastemangmentapplication1.Market_Place

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement // For Column spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues // For LazyColumn contentPadding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height // For Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Correct import for LazyColumn items extension
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Data class and sample data
data class Address(val id: Int, val fullName: String, val street: String, val city: String, val zipCode: String, val country: String)

val sampleAddresses = listOf(
    Address(1, "Dhruva Rajendra Khatavkar", "bapuji nagar", "Beed", "431122", "India"),
    Address(2, "Rakshit", "sector 128", "Noida", "201304", "India"),
    Address(3, "Vinay", "sector 104", "Noida", "201304", "India")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressSelectionScreen(
    navController: NavController,
    addresses: List<Address>,
    onContinue: (Address) -> Unit
) {
    var selectedAddress by remember { mutableStateOf<Address?>(addresses.firstOrNull()) }

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
            contentDescription = "Address Icon",
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFF4CAF50), CircleShape)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Select Shipping Address",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Address List
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(addresses, key = { it.id }) { address ->
                AddressItem(
                    address = address,
                    isSelected = address == selectedAddress,
                    onSelect = { selectedAddress = address }
                )
            }

            item {
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = {
                        navController.navigate("add_address_route")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("+ Add New Address", color = Color(0xFF4CAF50))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Continue Button
        Button(
            onClick = { selectedAddress?.let(onContinue) },
            enabled = selectedAddress != null,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Continue", color = Color.White)
        }
    }
}

@Composable
fun AddressItem(address: Address, isSelected: Boolean, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp),
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Spacer(Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(address.fullName, style = MaterialTheme.typography.titleMedium)
                Text(address.street, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("${address.city}, ${address.zipCode}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(address.country, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddressSelectionScreenPreview() {
    MaterialTheme {
        AddressSelectionScreen(
            navController = rememberNavController(),
            addresses = sampleAddresses,
            onContinue = { address -> println("Selected Address: ${address.fullName}") }
        )
    }
}

