package com.example.foodwastemangmentapplication1.NavigationController

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.foodwastemangmentapplication1.HomeScreen.DonorHomeScreen
import com.example.foodwastemangmentapplication1.LoginProcess.ForgotPasswordScreen
import com.example.foodwastemangmentapplication1.LoginProcess.LoginScreen
import com.example.foodwastemangmentapplication1.LoginProcess.SignUpScreen

fun NavGraphBuilder.authGraph(navController: NavController){

    navigation(startDestination = Screen.ScreenLoginRoute.route, route = Screen.AuthRoute.route){

        composable(route = Screen.ScreenLoginRoute.route){
            LoginScreen(navController = navController)
        }
        composable(route = Screen.ScreenSignUpProcessRoute.route){
            SignUpScreen(navController)
        }
        composable(route = Screen.ScreenForgotPasswordRoute.route){
            ForgotPasswordScreen(navController)
        }

    }
}