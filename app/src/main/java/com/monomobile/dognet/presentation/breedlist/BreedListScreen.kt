package com.monomobile.dognet.presentation.breedlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.monomobile.dognet.R
import com.monomobile.dognet.domain.model.Breed
import com.monomobile.dognet.presentation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun BreedListScreen(
    navController: NavController,
    viewModel: BreedListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    BreedListContent(
        state = state,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        onBreedClick = { breedName ->
            navController.navigate(Screen.BreedImages.createRoute(breedName))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedListContent(
    state: BreedListState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBreedClick: (String) -> Unit
) {
    var debouncedQuery by remember { mutableStateOf(searchQuery) }

    LaunchedEffect(searchQuery) {
        delay(300)
        debouncedQuery = searchQuery
    }

    val filteredBreeds = remember(state, debouncedQuery) {
        when (state) {
            is BreedListState.Success -> {
                state.breeds.filter {
                    it.name.contains(debouncedQuery, ignoreCase = true)
                }
            }
            else -> emptyList()
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = { Text(stringResource(R.string.search_breeds)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {

            when (state) {
                is BreedListState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is BreedListState.Error -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = state.message)
                    }
                }

                is BreedListState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(filteredBreeds) { breed ->
                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn(tween(300)),
                                exit = fadeOut(tween(300))
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                        .clickable { onBreedClick(breed.name) },
                                    elevation = CardDefaults.cardElevation(4.dp)
                                ) {
                                    Text(
                                        text = breed.name,
                                        modifier = Modifier.padding(16.dp),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BreedListContentPreview() {
    val mockState = BreedListState.Success(
        breeds = listOf(
            Breed("Husky"),
            Breed("Beagle"),
            Breed("Labrador")
        )
    )

    BreedListContent(
        state = mockState,
        searchQuery = "",
        onSearchQueryChange = {},
        onBreedClick = {}
    )
}
