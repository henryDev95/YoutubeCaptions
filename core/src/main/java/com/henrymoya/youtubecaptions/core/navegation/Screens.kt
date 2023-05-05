package com.henrymoya.youtubecaptions.core.navegation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("SplashScreen")
    object SearchScreen : Screens("SearchScreen")
}