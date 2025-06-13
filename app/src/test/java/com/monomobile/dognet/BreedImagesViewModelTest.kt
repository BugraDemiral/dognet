package com.monomobile.dognet

import app.cash.turbine.test
import com.monomobile.dognet.domain.model.Breed
import com.monomobile.dognet.domain.repository.DogRepository
import com.monomobile.dognet.presentation.breedimages.BreedImagesState
import com.monomobile.dognet.presentation.breedimages.BreedImagesViewModel
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
        viewModel = BreedImagesViewModel(null, repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `state emits Success when repository returns images`() = runTest {
        whenever(repository.getBreedImages(Breed("bulldog"))).thenReturn(listOf("url1", "url2"))

        viewModel.fetchBreedImages(Breed("bulldog"))

        viewModel.state.test {
            val loading = awaitItem()
            assertTrue(loading is BreedImagesState.Loading)

            val success = awaitItem()
            assertTrue(success is BreedImagesState.Success)
        }
    }

    @Test
    fun `state emits Error when repository throws`() = runTest {
        whenever(repository.getBreedImages(Breed("beagle"))).thenThrow(RuntimeException("API error"))

        viewModel.fetchBreedImages(Breed("beagle"))

        viewModel.state.test {
            val loading = awaitItem()
            assertTrue(loading is BreedImagesState.Loading)

            val error = awaitItem()
            assertTrue(error is BreedImagesState.Error)
        }
    }
}
