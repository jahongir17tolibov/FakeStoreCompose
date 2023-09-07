package com.jt17.fakestorecompose.core.navigation

import com.jt17.fakestorecompose.R

internal const val BOTTOM_NAV_ROUTE = "Bottom_navigation_screens"
internal const val ROOT_ROUTE = "navigation_root"

internal const val HOME_SCREEN_ROUTE = "home_screen"
internal const val CART_SCREEN_ROUTE = "cart_screen"
internal const val PROFILE_SCREEN_ROUTE = "profile_screen"

sealed class BottomNavBarDestinations(
    val route: String,
    val title: String,
    val icon: Int
) {

    data object Home : BottomNavBarDestinations(
        route = HOME_SCREEN_ROUTE,
        title = "Home",
        icon = R.drawable.round_home
    )

    data object Cart : BottomNavBarDestinations(
        route = CART_SCREEN_ROUTE,
        title = "Cart",
        icon = R.drawable.round_shopping_cart
    )

    data object Profile : BottomNavBarDestinations(
        route = PROFILE_SCREEN_ROUTE,
        title = "Profile",
        icon = R.drawable.round_profile
    )

}
