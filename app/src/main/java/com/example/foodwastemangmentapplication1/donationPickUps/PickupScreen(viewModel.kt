package com.example.foodwastemangmentapplication1.donationPickUps

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PickupScreen(viewModel: PickupViewModel, navController: NavController) {
    val context = LocalContext.current
    val donations = viewModel.donations
    LazyColumn {
        items(donations) { donation ->
            Card() {
                Column {
                    Text("Donor: ${donation.donorName}")
                    Text("Food: ${donation.foodDetails}")
                    Text("Location: ${donation.latitude}, ${donation.longitude}")

                    Row {
                        Button(onClick = {
                            if (donation.latitude != null && donation.longitude != null) {
//                                val uri = Uri.parse("geo:${donation.latitude},${donation.longitude}")
//                                val intent = Intent(Intent.ACTION_VIEW, uri)
//                                context.startActivity(intent)

                                val uri = Uri.parse(
                                    "https://www.google.com/maps/dir/?api=1&destination=${donation.latitude},${donation.longitude}&travelmode=driving"
                                )
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                intent.setPackage("com.google.android.apps.maps") // Optional: open specifically in Google Maps
                                try {
                                    context.startActivity(intent)
                                } catch (e: ActivityNotFoundException) {
                                    // Fallback if Maps not installed
                                    context.startActivity(
                                        Intent(Intent.ACTION_VIEW, Uri.parse("geo:${donation.latitude},${donation.longitude}"))
                                    )
                                }

                            }
                        }) {
                            Text("Navigate")
                        }

                        Spacer(Modifier.width(12.dp))

                        Button(
                            onClick = { //viewModel.deleteDonation(donation.id)
                                viewModel.deleteDonationByDonorName(donation.donorName)
                                      },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                        ) {
                            Text("Picked up")
                        }
                    }
                }
            }
        }
    }
}
