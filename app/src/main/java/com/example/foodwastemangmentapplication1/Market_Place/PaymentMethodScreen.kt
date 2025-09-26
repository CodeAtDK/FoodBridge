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
//import androidx.compose.foundation.lazy.items
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
//fun PaymentMethodScreen(
//    methods: List<PaymentMethod>,
//    onContinue: (PaymentMethod) -> Unit
//) {
//    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(methods.firstOrNull()) }
//
//    Scaffold(
//        topBar = { TopAppBar(title = { Text("Select Payment Method") }) },
//        bottomBar = {
//            Button(
//                onClick = { selectedMethod?.let(onContinue) },
//                enabled = selectedMethod != null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text("Confirm Payment")
//            }
//        }
//    ) { paddingValues ->
//        LazyColumn(contentPadding = paddingValues) {
//            items(methods) { method ->
//                PaymentMethodItem(
//                    method = method,
//                    isSelected = method == selectedMethod,
//                    onSelect = { selectedMethod = method }
//                )
//            }
//            item {
//                // Button to navigate to "Add New Card" form
//                TextButton(onClick = { /* Navigate to Add New Card Form */ }) {
//                    Text("+ Add New Card")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun PaymentMethodItem(method: PaymentMethod, isSelected: Boolean, onSelect: () -> Unit) {
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
//                // Display logo/icon based on method.type
//                Text(text = method.name, style = MaterialTheme.typography.subtitle1)
//                Text(text = method.detail, style = MaterialTheme.typography.body2)
//            }
//        }
//    }
//}
//data class PaymentMethod(val id: Int, val name: String, val type: String, val detail: String)
//val samplePaymentMethods = listOf(
//    PaymentMethod(1, "Visa ending in 1234", "Card", "Expires 12/25"),
//    PaymentMethod(2, "PayPal", "Wallet", "johndoe@email.com")
//)

package com.example.foodwastemangmentapplication1.Market_Place

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement // For Column spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height // For Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults // For M3 Card elevation
import androidx.compose.material3.ExperimentalMaterial3Api // For M3 TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults // For RadioButton colors (optional)
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults // For M3 TopAppBar colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview // For Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController // For navigation example
import androidx.navigation.compose.rememberNavController // For preview

// Data class and sample data (assuming these are defined as you provided)
data class PaymentMethod(val id: Int, val name: String, val type: String, val detail: String)

val samplePaymentMethods = listOf(
    PaymentMethod(1, "Visa ending in 1234", "Card", "Expires 12/25"),
    PaymentMethod(2, "Upi", "UPI", "aBc@email.com"),
    PaymentMethod(3, "Cash on Delivery ", "COD", "Pay at delivery")
)

@OptIn(ExperimentalMaterial3Api::class) // Required for Material3 TopAppBar
@Composable
fun PaymentMethodScreen(
    navController: NavController, // Added for "Add New Card" navigation
    methods: List<PaymentMethod>,
    onContinue: (PaymentMethod) -> Unit
) {
    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(methods.firstOrNull()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Payment Method") },
                colors = TopAppBarDefaults.topAppBarColors( // M3 way to set colors
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { selectedMethod?.let(onContinue) },
                enabled = selectedMethod != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding around the button
            ) {
                Text("Confirm Payment")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues), // Apply padding from Scaffold
            contentPadding = PaddingValues(vertical = 8.dp) // Additional padding for content inside LazyColumn
        ) {
            items(methods, key = { method -> method.id }) { method -> // Added key
                PaymentMethodItem(
                    method = method,
                    isSelected = method == selectedMethod,
                    onSelect = { selectedMethod = method }
                )
            }
            item {
                Spacer(Modifier.height(8.dp)) // Add some space before the button
                TextButton(
                    onClick = {
                        // Example: navController.navigate("add_card_route")
                        // Ensure "add_card_route" is defined in your NavHost
                        // For now, we'll just print, replace with actual navigation
                        println("Navigate to Add New Card Form")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp) // Align with card padding
                ) {
                    Text("+ Add New Card")
                }
            }
        }
    }
}

@Composable
fun PaymentMethodItem(method: PaymentMethod, isSelected: Boolean, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp) // Consistent horizontal padding
            .clickable(onClick = onSelect),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp), // M3 elevation
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
            else MaterialTheme.colorScheme.surfaceVariant // Differentiate selected card bg
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
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) { // Add spacing between texts
                // TODO: Display logo/icon based on method.type (e.g., using an Icon Composable)
                Text(text = method.name, style = MaterialTheme.typography.titleMedium) // M3 Typography
                Text(
                    text = method.detail,
                    style = MaterialTheme.typography.bodyMedium, // M3 Typography
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodScreenPreview() {
    // Basic MaterialTheme wrapper for preview
    MaterialTheme {
        PaymentMethodScreen(
            navController = rememberNavController(), // Dummy NavController for preview
            methods = samplePaymentMethods,
            onContinue = { method -> println("Selected: ${method.name}") }
        )
    }
}

