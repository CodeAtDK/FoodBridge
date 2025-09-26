package com.example.foodwastemangmentapplication1.NavigationController

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.foodwastemangmentapplication1.LoginProcess.LoginScreen

@Composable
fun Nav(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AuthRoute.route){

        authGraph(navController)
        HomeGraph(navController)

    }

}

