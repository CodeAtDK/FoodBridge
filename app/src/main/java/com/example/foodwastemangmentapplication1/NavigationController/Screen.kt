package com.example.foodwastemangmentapplication1.NavigationController

sealed class Screen(val route : String){

    object ScreenLoginRoute : Screen(route = "Login")
    object ScreenSignUpProcessRoute : Screen(route = "SignUpProcess")
    object ScreenForgotPasswordRoute : Screen(route = "ForgotPassword")
    object ScreenHomeRoute : Screen(route = "HomeScreenProcess")
    object ScreenDonationRoute : Screen(route = "DonationScreenProcess")
    object ScreenProfileRoute : Screen(route = "ProfileScreenProcess")
    object ScreenProductListRoute : Screen(route = "ProductListScreenProcess")
    object ScreenAddDonationsScreenRoute : Screen(route = "AddDonationsScreenRoute")
    object ScreenAddresSelectionRoute : Screen(route = "AddressSelectionScreenProcess")
    object ScreenPaymentMethodRoute : Screen(route = "PaymentsMethodsScreenProcess")
    object ScreenProductDetailsRoute : Screen(route = "product_detail_screen/{productId}")
    {
        fun createRoute(productId: String) = "product_detail_screen/$productId"
}
    object ScreenPickupScreenRoute : Screen(route = "PickupScreen")

    object AuthRoute : Screen(route = "Auth")
    object HomeRoute : Screen(route = "Home")
}




