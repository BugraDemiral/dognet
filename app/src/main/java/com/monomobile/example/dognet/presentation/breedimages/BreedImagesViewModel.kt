package com.monomobile.example.dognet.presentation.breedimages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monomobile.example.dognet.domain.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedImagesViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BreedImagesState>(BreedImagesState.Loading)
    val state: StateFlow<BreedImagesState> = _state

    fun fetchBreedImages(breed: String) {
        viewModelScope.launch {
            try {
                val images = repository.getBreedImages(breed)
                _state.value = BreedImagesState.Success(images)
            } catch (e: Exception) {
                _state.value = BreedImagesState.Error("Failed to load images")
            }
        }
    }
}

sealed class BreedImagesState {
    object Loading : BreedImagesState()
    data class Success(val images: List<String>) : BreedImagesState()
    data class Error(val message: String) : BreedImagesState()
}
