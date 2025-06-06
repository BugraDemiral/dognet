package com.monomobile.example.dognet

import app.cash.turbine.test
import com.monomobile.example.dognet.domain.repository.DogRepository
import com.monomobile.example.dognet.presentation.breedimages.BreedImagesState
import com.monomobile.example.dognet.presentation.breedimages.BreedImagesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class BreedImagesViewModelTest {

    private lateinit var repository: DogRepository
    private lateinit var viewModel: BreedImagesViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = BreedImagesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `state emits Success when repository returns images`() = runTest {
        whenever(repository.getBreedImages("bulldog")).thenReturn(listOf("url1", "url2"))

        viewModel.fetchBreedImages("bulldog")

        viewModel.state.test {
            val loading = awaitItem()
            assertTrue(loading is BreedImagesState.Loading)

            val success = awaitItem()
            assertTrue(success is BreedImagesState.Success)
        }
    }

    @Test
    fun `state emits Error when repository throws`() = runTest {
        whenever(repository.getBreedImages("beagle")).thenThrow(RuntimeException("API error"))

        viewModel.fetchBreedImages("beagle")

        viewModel.state.test {
            val loading = awaitItem()
            assertTrue(loading is BreedImagesState.Loading)

            val error = awaitItem()
            assertTrue(error is BreedImagesState.Error)
        }
    }
}
