package com.example.foodwastemangmentapplication1.NavigationController

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Nav(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AuthRoute.route){

        authGraph(navController)
        HomeGraph(navController)


    }




}

