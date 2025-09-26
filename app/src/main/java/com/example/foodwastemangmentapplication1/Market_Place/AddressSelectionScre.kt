//package com.example.foodwastemangmentapplication1.Market_Place
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun AddressSelectionScreen(
//    addresses: List<Address>,
//    onSelectAddress: (Address) -> Unit,
//    onContinue: (Address) -> Unit
//) {
//    var selectedAddress by remember { mutableStateOf<Address?>(addresses.firstOrNull()) }
//
//    Scaffold(
//        topBar = { TopAppBar(title = { Text("Select Shipping Address") }) },
//        bottomBar = {
//            Button(
//                onClick = { selectedAddress?.let(onContinue) },
//                enabled = selectedAddress != null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text("Continue")
//            }
//        }
//    ) { paddingValues ->
//        LazyColumn(contentPadding = paddingValues) {
//            items(addresses) { address ->
//                AddressItem(
//                    address = address,
//                    isSelected = address == selectedAddress,
//                    onSelect = { selectedAddress = address }
//                )
//            }
//            item {
//                // Button to navigate to "Add New Address" form
//                TextButton(onClick = { /* Navigate to Add New Address */ }) {
//                    Text("+ Add New Address")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun AddressItem(address: Address, isSelected: Boolean, onSelect: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp, vertical = 4.dp)
//            .clickable(onClick = onSelect),
//        elevation = 2.dp,
//        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colors.primary) else null
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            RadioButton(selected = isSelected, onClick = onSelect)
//            Spacer(Modifier.width(16.dp))
//            Column {
//                Text(text = address.fullName, style = MaterialTheme.typography.subtitle1)
//                Text(text = "${address.street}, ${address.city}", style = MaterialTheme.typography.body2)
//            }
//        }
//    }
//}
//data class Address(val id: Int, val fullName: String, val street: String, val city: String)
//val sampleAddresses = listOf(
//    Address(1, "John Doe", "123 Main St", "New York"),
//    Address(2, "Jane Smith", "456 Oak Ave", "Los Angeles")
//)

package com.example.foodwastemangmentapplication1.Market_Place

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement // For Column spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues // For LazyColumn contentPadding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height // For Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Correct import for LazyColumn items extension
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
    navController: NavController, // Added for "Add New Address" navigation
    addresses: List<Address>,
    // onSelectAddress: (Address) -> Unit, // This callback seems unused with current logic, consider removing if not needed
    onContinue: (Address) -> Unit
) {
    var selectedAddress by remember { mutableStateOf<Address?>(addresses.firstOrNull()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Shipping Address") },
                colors = TopAppBarDefaults.topAppBarColors( // M3 way to set colors
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { selectedAddress?.let(onContinue) },
                enabled = selectedAddress != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Continue")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues), // Apply padding from Scaffold
            contentPadding = PaddingValues(vertical = 8.dp) // Additional padding for content
        ) {
            items(addresses, key = { address -> address.id }) { address -> // Added key
                AddressItem(
                    address = address,
                    isSelected = address == selectedAddress,
                    onSelect = {
                        selectedAddress = address
                        // If you need to notify on every selection change:
                        // onSelectAddress(address)
                    }
                )
            }
            item {
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = {
                        // Example: navController.navigate("add_address_route")
                        // Ensure "add_address_route" is defined in your NavHost
                        println("Navigate to Add New Address Form")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp) // Align with card padding
                ) {
                    Text("+ Add New Address")
                }
            }
        }
    }
}

@Composable
fun AddressItem(address: Address, isSelected: Boolean, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp) // Consistent horizontal padding
            .clickable(onClick = onSelect),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp), // M3 elevation
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
                Text(
                    text = address.fullName,
                    style = MaterialTheme.typography.titleMedium // M3 Typography
                )
                Text(
                    text = address.street,
                    style = MaterialTheme.typography.bodyMedium, // M3 Typography
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${address.city}, ${address.zipCode}", // Added zipCode
                    style = MaterialTheme.typography.bodyMedium, // M3 Typography
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = address.country, // Added country
                    style = MaterialTheme.typography.bodySmall, // M3 Typography for less emphasis
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
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

