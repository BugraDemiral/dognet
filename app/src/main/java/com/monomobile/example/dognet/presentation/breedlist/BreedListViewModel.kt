package com.monomobile.example.dognet.presentation.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monomobile.example.dognet.domain.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BreedListState>(BreedListState.Loading)
    val state: StateFlow<BreedListState> = _state

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            try {
                val breeds = repository.getAllBreeds()
                _state.value = BreedListState.Success(breeds)
            } catch (e: Exception) {
                _state.value = BreedListState.Error("Failed to load breeds")
            }
        }
    }
}

sealed class BreedListState {
    object Loading : BreedListState()
    data class Success(val breeds: List<String>) : BreedListState()
    data class Error(val message: String) : BreedListState()
}
