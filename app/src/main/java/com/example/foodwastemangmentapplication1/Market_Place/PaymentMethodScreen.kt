package com.example.foodwastemangmentapplication1.Market_Place

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement // For Column spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height // For Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults // For M3 Card elevation
import androidx.compose.material3.ExperimentalMaterial3Api // For M3 TopAppBar
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview // For Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController // For navigation example
import androidx.navigation.compose.rememberNavController // For preview
data class PaymentMethod(val id: Int, val name: String, val type: String, val detail: String)

val samplePaymentMethods = listOf(
    PaymentMethod(1, "Visa ending in 1234", "Card", "Expires 12/25"),
    PaymentMethod(2, "Upi", "UPI", "aBc@email.com"),
    PaymentMethod(3, "Cash on Delivery ", "COD", "Pay at delivery")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen(
    navController: NavController,
    methods: List<PaymentMethod>,
    onContinue: (PaymentMethod) -> Unit
) {
    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(methods.firstOrNull()) }
    val context = LocalContext.current
    val upiPaymentResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            // Handle UPI response here
            Toast.makeText(context, "Payment success!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Payment failed or cancelled", Toast.LENGTH_SHORT).show()
        }
    }


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
            contentDescription = "Payment Icon",
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFF4CAF50), CircleShape)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Select Payment Method",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Payment Methods List
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(methods, key = { it.id }) { method ->
                PaymentMethodItem(
                    method = method,
                    isSelected = method == selectedMethod,
                    onSelect = { selectedMethod = method }
                )
            }

            item {
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = {
                      //  navController.navigate("add_card_route")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("+ Add New Card", color = Color(0xFF4CAF50))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Confirm Payment Button
        Button(
            onClick = {  //selectedMethod?.let(onContinue)
                //Upi()
                launchUpi(context, upiPaymentResultLauncher)
                      },
            enabled = selectedMethod != null,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Confirm Payment", color = Color.White)
        }
    }
}
fun launchUpi(context: Context, upiPaymentResultLauncher: ActivityResultLauncher<Intent>) {
    val upiId = "8830448351@ybl"
    val name = "Agri CONNECT"
    val note = "Product Name"
    val amount = "10.00"

    val uri = Uri.parse("upi://pay").buildUpon()
        .appendQueryParameter("pa", upiId)
        .appendQueryParameter("pn", name)
        .appendQueryParameter("tn", note)
        .appendQueryParameter("am", amount)
        .appendQueryParameter("cu", "INR")
        .build()

    val intent = Intent(Intent.ACTION_VIEW, uri)
    val chooser = Intent.createChooser(intent, "Pay with")

    if (chooser.resolveActivity(context.packageManager) != null) {
        upiPaymentResultLauncher.launch(chooser)
    } else {
        Toast.makeText(context, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show()
    }
}


@Composable
fun PaymentMethodItem(method: PaymentMethod, isSelected: Boolean, onSelect: () -> Unit) {
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
                // Optional: show card logo/icon based on method.type
                Text(method.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    method.detail,
                    style = MaterialTheme.typography.bodyMedium,
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

