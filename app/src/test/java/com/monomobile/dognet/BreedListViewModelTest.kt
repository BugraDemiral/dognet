package com.monomobile.dognet

import app.cash.turbine.test
import com.monomobile.dognet.domain.model.Breed
import com.monomobile.dognet.domain.repository.DogRepository
import com.monomobile.dognet.presentation.breedlist.BreedListState
import com.monomobile.dognet.presentation.breedlist.BreedListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class BreedListViewModelTest {

    private lateinit var repository: DogRepository
    private lateinit var viewModel: BreedListViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = BreedListViewModel(null, repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `state emits Success when repository returns breeds`() = runTest {
        whenever(repository.getAllBreeds()).thenReturn(listOf(Breed("bulldog"), Breed("beagle")))

        viewModel = BreedListViewModel(null, repository)

        viewModel.state.test {
            val loading = awaitItem()
            assertTrue(loading is BreedListState.Loading)

            val success = awaitItem()
            assertTrue(success is BreedListState.Success)
        }
    }

    @Test
    fun `state emits Error when repository throws`() = runTest {
        whenever(repository.getAllBreeds()).thenThrow(RuntimeException("Network error"))

        viewModel = BreedListViewModel(null, repository)

        viewModel.state.test {
            val loading = awaitItem()
            assertTrue(loading is BreedListState.Loading)

            val error = awaitItem()
            assertTrue(error is BreedListState.Error)
        }
    }
}
