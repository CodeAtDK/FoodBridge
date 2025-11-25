package com.example.foodwastemangmentapplication1.NavigationController

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.foodwastemangmentapplication1.Admin.AdminScreen
import com.example.foodwastemangmentapplication1.Admin.AdminViewModel
import com.example.foodwastemangmentapplication1.HomeScreen.ContactSupportScreen
import com.example.foodwastemangmentapplication1.donations.AddDonationScreen
import com.example.foodwastemangmentapplication1.donations.DonationHistoryScreen
import com.example.foodwastemangmentapplication1.donations.DonationViewModel
import com.example.foodwastemangmentapplication1.HomeScreen.DonorHomeScreen
import com.example.foodwastemangmentapplication1.HomeScreen.DonorProfileScreen
import com.example.foodwastemangmentapplication1.HomeScreen.EditProfileScreen
import com.example.foodwastemangmentapplication1.HomeScreen.PrivacySettingsScreen
import com.example.foodwastemangmentapplication1.HomeScreen.ProfileViewModel
import com.example.foodwastemangmentapplication1.Market_Place.AddToCartScreen
import com.example.foodwastemangmentapplication1.Market_Place.AddressSelectionScreen
import com.example.foodwastemangmentapplication1.Market_Place.CartViewModel
import com.example.foodwastemangmentapplication1.Market_Place.PaymentMethodScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductDetailScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductListScreen
import com.example.foodwastemangmentapplication1.Market_Place.ProductViewModel
import com.example.foodwastemangmentapplication1.Market_Place.sampleAddresses
import com.example.foodwastemangmentapplication1.Market_Place.samplePaymentMethods

import com.example.foodwastemangmentapplication1.donationPickUps.PickupScreen
import com.example.foodwastemangmentapplication1.donationPickUps.PickupViewModel
import com.example.foodwastemangmentapplication1.donations.DonationHistoryViewModel
import com.example.foodwastemangmentapplication1.fssaiverify.AttestrApi
import com.example.foodwastemangmentapplication1.fssaiverify.VerifierScreen
import com.example.foodwastemangmentapplication1.fssaiverify.VerifierViewModel
import com.example.foodwastemangmentapplication1.fssaiverify.VerifierViewModelFactory
import com.example.foodwastemangmentapplication1.verfications.DocumentUploadScreen
import com.example.foodwastemangmentapplication1.verfications.DocumentUploadViewModel
import com.example.foodwastemangmentapplication1.verfications.DocumentVerification
import com.google.android.datatransport.BuildConfig


fun NavGraphBuilder.HomeGraph(navController: NavController){

    navigation(startDestination = Screen.ScreenHomeRoute.route, route = Screen.HomeRoute.route) {

        composable(route = Screen.ScreenHomeRoute.route) {
            val donataionhistory: DonationHistoryViewModel = viewModel()
            DonorHomeScreen(navController = navController, viewModel  = donataionhistory)
        }

        composable(route = Screen.ScreenDonationRoute.route) {
            val donataionhistory: DonationHistoryViewModel = viewModel()
            DonationHistoryScreen(navController = navController, viewModel  = donataionhistory)
        }

        composable(route = Screen.ScreenProfileRoute.route) {
            DonorProfileScreen(navController)
        }

        composable(route = Screen.ScreenAddDonationsScreenRoute.route) { navBackStackEntry ->

            val donationViewModel: DonationViewModel = viewModel()
            AddDonationScreen(viewModel = donationViewModel, navController = navController,)
        }

        composable(route = Screen.ScreenPickupScreenRoute.route) { navBackStackEntry ->

            val PickupScreenViewModel: PickupViewModel = viewModel()
            PickupScreen(viewModel = PickupScreenViewModel, navController = navController,)
        }

        composable(route = Screen.ScreenCartScreenPage.route) { navBackStackEntry ->

            val CartViewModel: CartViewModel = viewModel()
            AddToCartScreen(navController = navController,viewModel = CartViewModel)
        }

        composable(route = Screen.ScreenDocumentVerification.route) { navBackStackEntry ->

            val Documentverfiyi: DocumentUploadViewModel = viewModel()
            DocumentUploadScreen(navController = navController,viewModel = Documentverfiyi)
        }

        composable(route = Screen.ScreenAdmin.route) { navBackStackEntry ->

            val AdminViewModel: AdminViewModel = viewModel()
            AdminScreen(navController = navController,viewModel = AdminViewModel)
        }

        composable(route = Screen.ScreenEditProfile.route) { navBackStackEntry ->

            val ProfileViewModel: ProfileViewModel = viewModel()
            EditProfileScreen(navController = navController,viewModel = ProfileViewModel)
        }

        composable(route = Screen.ScreenContactSupport.route) {

            ContactSupportScreen(navController)
        }

        composable(route = Screen.ScreenPrivacySettingScreen.route) {

            PrivacySettingsScreen(navController)
        }

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
            route = Screen.ScreenVerifierRoute.route
        ) { navBackStackEntry ->

            val context = LocalContext.current
            val viewModel: VerifierViewModel = viewModel(
            )
            VerifierScreen(viewModel = viewModel,context)
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

                }
            )
        }


    }
}


