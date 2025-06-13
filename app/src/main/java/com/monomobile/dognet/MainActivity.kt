package com.monomobile.dognet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.monomobile.dognet.presentation.navigation.DogNavHost
import com.monomobile.example.dognet.ui.theme.DogNetTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogNetTheme {
                DogNavHost()
            }
        }
    }
}
