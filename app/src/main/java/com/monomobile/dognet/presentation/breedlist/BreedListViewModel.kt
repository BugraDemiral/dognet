package com.monomobile.dognet.presentation.breedlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monomobile.dognet.R
import com.monomobile.dognet.domain.model.Breed
import com.monomobile.dognet.domain.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BreedListViewModel @Inject constructor(
    @ApplicationContext private val context: Context?,
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
                _state.value = BreedListState.Error(
                    context?.getString(R.string.error_loading_breeds) ?: run {
                        e.localizedMessage
                    })
            }
        }
    }
}

sealed class BreedListState {
    data object Loading : BreedListState()
    data class Success(val breeds: List<Breed>) : BreedListState()
    data class Error(val message: String) : BreedListState()
}
