package com.jt17.fakestorecompose.core.navigation

import com.jt17.fakestorecompose.R

internal const val LOGIN_SCREEN_ROUTE = "login_screen_route"
internal const val SIGNUP_SCREEN_ROUTE = "sign_up_screen_route"
internal const val FAVOURITES_SCREEN_ROUTE = "favourites_screen"

internal const val AUTH_ROUTE = "auth_route"
internal const val ROOT_ROUTE = "navigation_root"

sealed class Destinations(val route: String) {
    data object Login : Destinations(route = LOGIN_SCREEN_ROUTE)
    data object SignUp : Destinations(route = SIGNUP_SCREEN_ROUTE)
    data object Favourites : Destinations(route = FAVOURITES_SCREEN_ROUTE)
}
