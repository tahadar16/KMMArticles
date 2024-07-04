package com.tahadev.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tahadev.dailypulse.android.screens.AboutScreen
import com.tahadev.dailypulse.android.screens.ArticlesScreen
import com.tahadev.dailypulse.android.screens.ScreenRoutes

@Composable
fun AppScaffold(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold {
        AppHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController
        )
    }
}

@Composable
fun AppHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreenRoutes.ARTICLES_SCREEN.route
    ) {
        composable(ScreenRoutes.ARTICLES_SCREEN.route) {
            ArticlesScreen {
                navController.navigate(ScreenRoutes.ABOUT_SCREEN.route)
            }
        }
        composable(ScreenRoutes.ABOUT_SCREEN.route) {
            AboutScreen {
                navController.navigateUp()
            }
        }
    }
}