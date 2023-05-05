package com.henrymoya.youtubecaptions.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.henrymoya.youtubecaptions.core.navegation.Screens
import com.henrymoya.youtubecaptions.search.SearchActivity
import com.henrymoya.youtubecaptions.ui.screen.SplashScreen
import com.henrymoya.youtubecaptions.uikit.style.YoutubeCaptionsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private var startDestination = Screens.SplashScreen.route
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            YoutubeCaptionsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashApp()
                }
            }
        }
    }

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class,
        ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
        ExperimentalComposeUiApi::class,
    )
    @Composable
    fun SplashApp() {
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberAnimatedNavController(bottomSheetNavigator)

        ModalBottomSheetLayout(bottomSheetNavigator) {
            AnimatedNavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(Screens.SplashScreen.route) {
                    SplashScreen(
                        onNavigateToDashboard = {

                            val intent = Intent(applicationContext, SearchActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                    )
                }
            }
        }

    }
}

