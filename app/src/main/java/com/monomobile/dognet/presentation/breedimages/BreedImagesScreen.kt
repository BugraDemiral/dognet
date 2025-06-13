package com.monomobile.dognet.presentation.breedimages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.monomobile.dognet.R
import com.monomobile.dognet.domain.model.Breed

@Composable
fun BreedImagesScreen(
    breed: Breed,
    viewModel: BreedImagesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(breed.name) {
        viewModel.fetchBreedImages(breed)
    }

    BreedImagesContent(
        breedName = breed.name,
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedImagesContent(
    breedName: String,
    state: BreedImagesState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.image_grid_title) + " ${breedName.replaceFirstChar { it.uppercase() }}") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is BreedImagesState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 4.dp
                        )
                    }
                }

                is BreedImagesState.Success -> {
                    val images = state.images
                    AnimatedVisibility(
                        visible = images.isNotEmpty(),
                        enter = fadeIn(tween(500)),
                        exit = fadeOut(tween(500))
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(images) { url ->
                                Image(
                                    painter = rememberAsyncImagePainter(model = url),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(12.dp))
                                        .border(
                                            1.dp,
                                            Color.LightGray,
                                            RoundedCornerShape(12.dp)
                                        )
                                )
                            }
                        }
                    }
                }

                is BreedImagesState.Error -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BreedImagesContentPreview() {
    val mockState = BreedImagesState.Success(
        images = listOf(
            "https://images.dog.ceo/breeds/husky/n02110185_1469.jpg",
            "https://images.dog.ceo/breeds/husky/n02110185_2593.jpg"
        )
    )

    BreedImagesContent(
        breedName = "Husky",
        state = mockState
    )
}
