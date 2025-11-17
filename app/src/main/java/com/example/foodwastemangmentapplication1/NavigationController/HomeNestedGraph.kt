package com.example.foodwastemangmentapplication1.NavigationController

import android.util.Log
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.foodwastemangmentapplication1.donations.AddDonationScreen
import com.example.foodwastemangmentapplication1.donations.DonationHistoryScreen
import com.example.foodwastemangmentapplication1.donations.DonationViewModel
import com.example.foodwastemangmentapplication1.HomeScreen.DonorHomeScreen
import com.example.foodwastemangmentapplication1.HomeScreen.DonorProfileScreen
import com.example.foodwastemangmentapplication1.Market_Place.AddressSelectionScreen
import com.example.foodwastemangmentapplication1.Market_Place.PaymentMethodScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductDetailScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductListScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductViewModel
import com.example.foodwastemangmentapplication1.Market_Place.sampleAddresses
import com.example.foodwastemangmentapplication1.Market_Place.samplePaymentMethods

import com.example.foodwastemangmentapplication1.donationPickUps.PickupScreen
import com.example.foodwastemangmentapplication1.donationPickUps.PickupViewModel
import com.example.foodwastemangmentapplication1.donations.DonationHistoryViewModel


fun NavGraphBuilder.HomeGraph(navController: NavController){

    navigation(startDestination = Screen.ScreenHomeRoute.route, route = Screen.HomeRoute.route) {

        composable(route = Screen.ScreenHomeRoute.route) {
            DonorHomeScreen(navController)
        }

        composable(route = Screen.ScreenDonationRoute.route) {
            val donataionhistory: DonationHistoryViewModel = viewModel()
            DonationHistoryScreen(navController = navController, viewModel  = donataionhistory)
        }
        composable(route = Screen.ScreenProfileRoute.route) {
            DonorProfileScreen(navController)
        }
        composable(route = Screen.ScreenAddDonationsScreenRoute.route) { navBackStackEntry ->
            // Use viewModel() (for default) or hiltViewModel() (if using Hilt DI)
            val donationViewModel: DonationViewModel = viewModel()
            AddDonationScreen(viewModel = donationViewModel, navController = navController,)
        }
        composable(route = Screen.ScreenPickupScreenRoute.route) { navBackStackEntry ->
            // Use viewModel() (for default) or hiltViewModel() (if using Hilt DI)
            val PickupScreenViewModel: PickupViewModel = viewModel()
            PickupScreen(viewModel = PickupScreenViewModel, navController = navController,)
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

        composable(route = Screen.ScreenProductListRoute.route) { backStackEntry ->
            val productList: ProductViewModel = viewModel()
            ProductListScreen(
                navController = navController,
                viewModel = productList,
                onProductClick = { product ->
                   // navController.navigate(Screen.ScreenProductDetailsRoute.createRoute(product.id))
                    navController.navigate(Screen.ScreenProductDetailsRoute.createRoute(product.id))

                },
                onSearchClick = {
                    Log.d("HomeGraph", "Search clicked")
                }
            )
        }


        composable(
            route = Screen.ScreenProductDetailsRoute.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val viewModel = viewModel<ProductViewModel>()
            val product = viewModel.products.find { it.id == productId }

            product?.let { selectedProduct ->
                ProductDetailScreen(
                    navController = navController,
                    product = selectedProduct,
                    onAddToCart = { cartProduct ->
                        // Handle add to cart logic
                    }
                )
            } ?: run {
                Text("Product not found")
            }
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


