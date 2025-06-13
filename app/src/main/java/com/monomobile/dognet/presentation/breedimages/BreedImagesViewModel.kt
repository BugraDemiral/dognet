package com.monomobile.dognet.presentation.breedimages

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monomobile.dognet.R
import com.monomobile.dognet.domain.model.Breed
import com.monomobile.dognet.domain.repository.DogRepository
import com.monomobile.dognet.presentation.breedlist.BreedListState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BreedImagesViewModel @Inject constructor(
    @ApplicationContext private val context: Context?,
    private val repository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BreedImagesState>(BreedImagesState.Loading)
    val state: StateFlow<BreedImagesState> = _state

    fun fetchBreedImages(breed: Breed) {
        viewModelScope.launch {
            try {
                val images = repository.getBreedImages(breed)
                _state.value = BreedImagesState.Success(images)
            } catch (e: Exception) {
                _state.value = BreedImagesState.Error(
                    context?.getString(R.string.error_loading_images) ?: run {
                        e.localizedMessage
                    })
            }
        }
    }
}

sealed class BreedImagesState {
    data object Loading : BreedImagesState()
    data class Success(val images: List<String>) : BreedImagesState()
    data class Error(val message: String) : BreedImagesState()
}
