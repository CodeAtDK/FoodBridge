package com.example.foodwastemangmentapplication1.donations

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

//@Composable
//fun AddDonationScreen(
//    viewModel: DonationViewModel = viewModel(),
//    navController: NavController,
//    initialLat: Double? = null,
//    initialLon: Double? = null
//) {
//    var donorName by remember { mutableStateOf("") }
//    var contact by remember { mutableStateOf("") }
//    var foodDetails by remember { mutableStateOf("") }
//    var isPackaged by remember { mutableStateOf(true) }
//    var latitude by remember { mutableStateOf(initialLat?.toString() ?: "") }
//    var longitude by remember { mutableStateOf(initialLon?.toString() ?: "") }
//    var errorMessage by remember { mutableStateOf("") }
//
//
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .verticalScroll(rememberScrollState())
//    ) {
//        Text(text = "Add Food Donation", style = MaterialTheme.typography.titleLarge)
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        TextField(
//            value = donorName,
//            onValueChange = { donorName = it },
//            label = { Text("Donor Name") },
//            singleLine = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        TextField(
//            value = contact,
//            onValueChange = { contact = it },
//            label = { Text("Contact Number") },
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        TextField(
//            value = foodDetails,
//            onValueChange = { foodDetails = it },
//            label = { Text("Food Details") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Text(text = "Packaged?")
//            Spacer(modifier = Modifier.width(8.dp))
//            Switch(
//                checked = isPackaged,
//                onCheckedChange = { isPackaged = it }
//            )
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(text = "Location Coordinates (Optional)", style = MaterialTheme.typography.bodyMedium)
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//            TextField(
//                value = latitude,
//                onValueChange = { latitude = it },
//                label = { Text("Latitude") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                modifier = Modifier.weight(1f)
//            )
//            TextField(
//                value = longitude,
//                onValueChange = { longitude = it },
//                label = { Text("Longitude") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                modifier = Modifier.weight(1f)
//            )
//        }
//
//        if (errorMessage.isNotEmpty()) {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                val lat = latitude.toDoubleOrNull()
//                val lon = longitude.toDoubleOrNull()
//                if (donorName.isBlank() || contact.isBlank() || foodDetails.isBlank()) {
//                    errorMessage = "Please fill all required fields"
//                } else if (latitude.isNotBlank() && longitude.isNotBlank() && (lat == null || lon == null)) {
//                    errorMessage = "Please enter valid coordinates"
//                } else {
//                    errorMessage = ""
//                    val donation = Donation(donorName, contact, foodDetails, isPackaged, lat, lon)
//                    viewModel.addDonation(donation)
//                    navController.popBackStack() // navigate back after adding
//                }
//
//
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Add Donation")
//        }
//    }
//}

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
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Add Food Donation", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = donorName,
            onValueChange = { donorName = it },
            label = { Text("Donor Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = contact,
            onValueChange = { contact = it },
            label = { Text("Contact Number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = foodDetails,
            onValueChange = { foodDetails = it },
            label = { Text("Food Details") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

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
                        latitude = null, // will be filled by ViewModel
                        longitude = null
                    )
                    viewModel.addDonation(donation)
                    navController.popBackStack()
                    Log.d("AddDonationScreen", "function called")

                }
                Log.d("AddDonationScreen", "Button clicked, calling addDonation")

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Donation")
        }
    }
}

