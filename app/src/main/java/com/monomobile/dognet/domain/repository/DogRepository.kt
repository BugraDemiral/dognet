package com.monomobile.dognet.domain.repository

import com.monomobile.dognet.domain.model.Breed

interface DogRepository {
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getBreedImages(breed: Breed): List<String>
}
