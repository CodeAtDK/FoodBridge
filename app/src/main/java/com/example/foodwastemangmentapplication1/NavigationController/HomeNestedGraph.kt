package com.example.foodwastemangmentapplication1.NavigationController

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.foodwastemangmentapplication1.HomeScreen.DonationHistoryScreen
import com.example.foodwastemangmentapplication1.HomeScreen.DonorHomeScreen
import com.example.foodwastemangmentapplication1.HomeScreen.DonorProfileScreen
import com.example.foodwastemangmentapplication1.Market_Place.AddressSelectionScreen
import com.example.foodwastemangmentapplication1.Market_Place.PaymentMethodScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductDetailScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductListScreen
import com.example.foodwastemangmentapplication1.Market_Place.sampleAddresses
import com.example.foodwastemangmentapplication1.Market_Place.samplePaymentMethods
import com.example.foodwastemangmentapplication1.Market_Place.sampleProduct
import com.example.foodwastemangmentapplication1.Market_Place.sampleProductsList


fun NavGraphBuilder.HomeGraph(navController: NavController){

    navigation(startDestination = Screen.ScreenHomeRoute.route, route = Screen.HomeRoute.route) {

        composable(route = Screen.ScreenHomeRoute.route) {
            DonorHomeScreen(navController)
        }

        composable(route = Screen.ScreenDonationRoute.route) {
            DonationHistoryScreen(navController)
        }
        composable(route = Screen.ScreenProfileRoute.route) {
            DonorProfileScreen(navController)
        }

//        composable(route = Screen.ScreenProductListRoute.route) {
//            ProductListScreen(navController)
//        }
//        composable(route = Screen.ScreenProductDetailsRoute.route) {
//            ProductDetailScreen(navController)
//        }
//        composable(route = Screen.ScreenAddresSelectionRoute.route) {
//            AddressSelectionScreen(navController)
//        }
//        composable(route = Screen.ScreenPaymentMethodRoute.route) {
//            PaymentMethod(navController,)
//        }

        composable(route = Screen.ScreenProductListRoute.route) {
            ProductListScreen(
                navController = navController, // Pass NavController if ProductListScreen uses it internally for other things
                products = sampleProductsList, // Pass sample data
                onProductClick = { product ->
                    // Navigate to product detail, passing product ID
                    navController.navigate(Screen.ScreenProductDetailsRoute.createRoute(product.id))
                },
                onSearchClick = {
                    // Handle search action, e.g., navigate to a search screen or show a search bar
                    Log.d("HomeGraph", "Search clicked")
                }
            )
        }

        composable(
            route = Screen.ScreenProductDetailsRoute.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            // In a real app, you'd fetch the product using the productId from a ViewModel/Repository
            val product = sampleProductsList.find { it.id == productId } ?: sampleProduct // Fallback to sampleProduct if not found
            ProductDetailScreen(
                navController = navController,
                product = product,
                onAddToCart = { cartProduct ->
                    Log.d("HomeGraph", "Add to cart: ${cartProduct.name}")
                    // Handle add to cart logic (e.g., update ViewModel, show Toast)
                }
            )
        }

        composable(route = Screen.ScreenAddresSelectionRoute.route) {
            AddressSelectionScreen(
                navController = navController,
                addresses = sampleAddresses, // Pass sample data
                onContinue = { address ->
                    Log.d("HomeGraph", "Selected Address: ${address.fullName}")
                    // Navigate to next step, e.g., payment, or pass data back
                    navController.navigate(Screen.ScreenPaymentMethodRoute.route) // Example navigation
                }
            )
        }

        composable(route = Screen.ScreenPaymentMethodRoute.route) {
            PaymentMethodScreen( // Corrected to call the Composable function
                navController = navController,
                methods = samplePaymentMethods, // Pass sample data
                onContinue = { paymentMethod ->
                    Log.d("HomeGraph", "Selected Payment: ${paymentMethod.name}")
                    // Navigate to order summary or confirmation
                    // navController.navigate("order_summary_route")
                }
            )
        }

//        composable(route = Screen..route) {
//            (navController)
//        }
//        composable(route = Screen..route) {
//            (navController)
//        }

    }
}
