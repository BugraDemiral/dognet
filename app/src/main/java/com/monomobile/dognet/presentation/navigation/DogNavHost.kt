package com.monomobile.dognet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.monomobile.dognet.domain.model.Breed
import com.monomobile.dognet.presentation.breedimages.BreedImagesScreen
import com.monomobile.dognet.presentation.breedlist.BreedListScreen

@Composable
fun DogNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BreedList.route
    ) {
        composable(route = Screen.BreedList.route) {
            BreedListScreen(navController)
        }

        composable(
            route = Screen.BreedImages.route,
            arguments = listOf(navArgument("breedName") { type = NavType.StringType })
        ) { backStackEntry ->
            val breedName = backStackEntry.arguments?.getString("breedName") ?: ""
            BreedImagesScreen(Breed(breedName))
        }
    }
}

