package com.example.foodwastemangmentapplication1.fssaiverify



import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class VerifierViewModel(
    private val api: AttestrApi,
    private val contextProvider: ()->Context
) : ViewModel() {

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        data class ImageShown(val uri: Uri) : UiState()
        data class Result(val fssai: String, val status: String?, val businessName: String?, val expiry: String?) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    fun processImageUri(uri: Uri) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val ctx = contextProvider()
            val bitmap = ImageUtils.loadBitmapFromUri(ctx, uri)
            if (bitmap == null) {
                _uiState.value = UiState.Error("Failed to load image")
                return@launch
            }
            _uiState.value = UiState.ImageShown(uri)
            runOcrAndVerify(bitmap)
        }
    }

    private fun runOcrAndVerify(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)


        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val text = visionText.text ?: ""
                val candidate = extractFssai(text)
                if (candidate == null) {
                    _uiState.value = UiState.Error("No 14-digit FSSAI number found")
                } else {
                    viewModelScope.launch {
                        try {
                            val req = VerifyRequest(fssaiNumber = candidate)
                            val resp: VerifyResponse = api.verifyFssai(req)
                            _uiState.value = UiState.Result(candidate, resp.status, resp.businessName, resp.expiry)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            _uiState.value = UiState.Error("Verification API error: ${e.message}")
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                _uiState.value = UiState.Error("OCR failed: ${e.message}")
            }


    }



    private fun extractFssai(text: String): String? {
        val cleaned = text.replace("\n"," ").replace("[^0-9 ]".toRegex(), " ")
        val p = Pattern.compile("\\b(\\d{14})\\b")
        val m = p.matcher(cleaned)
        return if (m.find()) m.group(1) else null
    }
}
