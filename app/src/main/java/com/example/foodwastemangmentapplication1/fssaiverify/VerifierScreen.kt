package com.example.foodwastemangmentapplication1.fssaiverify

import android.Manifest
import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VerifierScreen(viewModel: VerifierViewModel, context: Context) {
    val uiState by viewModel.uiState.collectAsState(initial = VerifierViewModel.UiState.Idle)

    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.processImageUri(it) }
    }
    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        // noop; GetContent will prompt storage/gallery as needed
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("FSSAI Verifier", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE) ; pickImageLauncher.launch("image/*") }) {
                Text("Pick Image")
            }
        }
        Spacer(Modifier.height(16.dp))

        when (uiState) {
            is VerifierViewModel.UiState.Idle -> Text("Upload or capture an FSSAI certificate")
            is VerifierViewModel.UiState.Loading -> CircularProgressIndicator()
            is VerifierViewModel.UiState.ImageShown -> {
                val uri = (uiState as VerifierViewModel.UiState.ImageShown).uri
                Image(painter = rememberAsyncImagePainter(uri), contentDescription = null, modifier = Modifier.fillMaxWidth().height(240.dp))
            }
            is VerifierViewModel.UiState.Result -> {
                val r = uiState as VerifierViewModel.UiState.Result
                Text("Extracted FSSAI: ${r.fssai}")
                Text("Status: ${r.status ?: "unknown"}")
                Text("Business: ${r.businessName ?: "-"}")
                Text("Expiry: ${r.expiry ?: "-"}")
                Spacer(Modifier.height(8.dp))
                if (r.status != null && r.status.lowercase().contains("active")) {
                    Text("This license appears ACTIVE. You may accept donation.")
                } else {
                    Text("License inactive/uncertain — flag for manual review.")
                    Button(onClick = { /* mark for manual review flow */ }) { Text("Flag for Review") }
                }
            }
            is VerifierViewModel.UiState.Error -> {
                Text("Error: ${(uiState as VerifierViewModel.UiState.Error).message}")
            }
        }
    }
}
