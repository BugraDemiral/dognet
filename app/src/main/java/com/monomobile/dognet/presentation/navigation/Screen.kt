package com.monomobile.dognet.presentation.navigation

sealed class Screen(val route: String) {
    data object BreedList : Screen("breed_list")
    data object BreedImages : Screen("breed_images/{breedName}") {
        fun createRoute(breedName: String) = "breed_images/$breedName"
    }
}
