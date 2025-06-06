package com.monomobile.example.dognet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.monomobile.example.dognet.presentation.breedimages.BreedImagesScreen
import com.monomobile.example.dognet.presentation.breedlist.BreedListScreen

@Composable
fun DogNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "breed_list") {
        composable("breed_list") {
            BreedListScreen(navController)
        }
        composable(
            route = "breed_images/{breed}",
            arguments = listOf(navArgument("breed") { type = NavType.StringType })
        ) { backStackEntry ->
            val breed = backStackEntry.arguments?.getString("breed") ?: return@composable
            BreedImagesScreen(breed)
        }
    }
}
