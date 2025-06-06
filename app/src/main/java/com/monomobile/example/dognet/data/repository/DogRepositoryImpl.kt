package com.monomobile.example.dognet.data.repository

import com.monomobile.example.dognet.data.remote.DogApi
import com.monomobile.example.dognet.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val api: DogApi
) : DogRepository {

    override suspend fun getAllBreeds(): List<String> {
        val response = api.getAllBreeds()
        return response.message.keys.toList()
    }

    override suspend fun getBreedImages(breed: String): List<String> {
        val response = api.getBreedImages(breed)
        return response.message
    }
}
