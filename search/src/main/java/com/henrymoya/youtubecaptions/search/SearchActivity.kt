package com.henrymoya.youtubecaptions.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.henrymoya.youtubecaptions.core.navegation.Screens
import com.henrymoya.youtubecaptions.search.ui.screen.SearchScreen
import com.henrymoya.youtubecaptions.uikit.style.YoutubeCaptionsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    private var startDestination = Screens.SearchScreen.route
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeCaptionsTheme {
                SearchApp()
            }
        }
    }

    @OptIn(ExperimentalMaterialNavigationApi::class)
    @Composable
    fun SearchApp() {
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberNavController(bottomSheetNavigator)

        ModalBottomSheetLayout(bottomSheetNavigator) {
            NavHost(
                navController,
                startDestination = startDestination
            ) {
                composable(
                    Screens.SearchScreen.route,
                ) {
                    SearchScreen()
                }
            }
        }
    }
}








