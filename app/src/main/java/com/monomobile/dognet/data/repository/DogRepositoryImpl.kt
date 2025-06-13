package com.monomobile.dognet.data.repository

import com.monomobile.dognet.data.remote.DogApi
import com.monomobile.dognet.domain.model.Breed
import com.monomobile.dognet.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val api: DogApi
) : DogRepository {

    override suspend fun getAllBreeds(): List<Breed> {
        val response = api.getAllBreeds()
        return response.message.keys.map { Breed(it) }
    }

    override suspend fun getBreedImages(breed: Breed): List<String> {
        val response = api.getBreedImages(breed.name)
        return response.message
    }
}

