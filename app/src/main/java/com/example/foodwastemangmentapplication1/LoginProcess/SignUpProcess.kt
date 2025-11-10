package com.example.foodwastemangmentapplication1.LoginProcess

// import androidx.navigation.NavController // Removed as it's not used
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodwastemangmentapplication1.MainActivity
import com.example.foodwastemangmentapplication1.NavigationController.Screen
import com.example.foodwastemangmentapplication1.ui.theme.FoodWasteMangmentApplication1Theme

@Composable
fun SignUpScreen(navController: NavController){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var Name by remember { mutableStateOf("") }
    var ConfirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {




        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Application Logo", // Corrected typo
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFF4CAF50), CircleShape)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Name
        OutlinedTextField(
            value = Name,
            onValueChange = { Name = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

       //// Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

       // Spacer(modifier = Modifier.height(16.dp))

        // Password Field with Eye Icon
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Filled.Clear
                        } else Icons.Filled.Edit,
//                          imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,

                        contentDescription = "Toggle Password Visibility"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

       // Spacer(modifier = Modifier.height(16.dp))

        // Password Field with Eye Icon
        OutlinedTextField(
            value = ConfirmPassword,
            onValueChange = { ConfirmPassword = it },
            label = { Text("ConfirmPassword") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Filled.Clear
                        } else Icons.Filled.Edit,
//                          imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,

                        contentDescription = "Toggle Password Visibility"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sign Up Button
        Button(
            onClick = {
                if (Name.isNotBlank() && email.isNotBlank() && password == ConfirmPassword) {
                    MainActivity.firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                                navController.navigate(Screen.ScreenHomeRoute.route)
                            } else {
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                                Log.e(TAG, "Sign-up failed", task.exception)
                            }
                        }
                } else {
                    Toast.makeText(context, "Please check your inputs", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Sign Up", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Forgot Password and Sign Up Links
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Already have an account?",
                color = Color.Black,
                //modifier = Modifier.clickable { /* Handle forgot password */ }
            )
            Text(
                text = "Log in",
                color = Color(0xFF4CAF50),
                modifier = Modifier.clickable {
                    navController.navigate(Screen.ScreenLoginRoute.route)
                }
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodWasteMangmentApplication1Theme {
      //  Greeting("Android")
        //LoginScreen()
//        SignUp()
    }
}