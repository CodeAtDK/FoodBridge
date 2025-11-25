package com.example.foodwastemangmentapplication1.donations

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AddDonationScreen(
    viewModel: DonationViewModel = viewModel(),
    navController: NavController
) {
    var donorName by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var foodDetails by remember { mutableStateOf("") }
    var isPackaged by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header Icon (like LoginScreen)
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Donation Icon",
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFF4CAF50), CircleShape)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Add Food Donation",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Donor Name Field
        OutlinedTextField(
            value = donorName,
            onValueChange = { donorName = it },
            label = { Text("Donor Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Contact Field
        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            label = { Text("Contact Number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Food Details Field
        OutlinedTextField(
            value = foodDetails,
            onValueChange = { foodDetails = it },
            label = { Text("Food Details") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Packaged Switch
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Packaged?")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isPackaged,
                onCheckedChange = { isPackaged = it }
            )
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add Donation Button
        Button(
            onClick = {
                if (donorName.isBlank() || contact.isBlank() || foodDetails.isBlank()) {
                    errorMessage = "Please fill all required fields"
                } else {
                    errorMessage = ""
                    val donation = Donation(
                        donorName = donorName,
                        contact = contact,
                        foodDetails = foodDetails,
                        packaged = isPackaged,
                        latitude = null,
                        longitude = null
                    )
                    viewModel.addDonation(donation)
                    navController.popBackStack()
                    Log.d("AddDonationScreen", "function called")
                }
                Log.d("AddDonationScreen", "Button clicked, calling addDonation")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Add Donation", color = Color.White)
        }
    }
}

