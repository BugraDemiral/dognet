package com.monomobile.example.dognet.domain.repository

interface DogRepository {
    suspend fun getAllBreeds(): List<String>
    suspend fun getBreedImages(breed: String): List<String>
}
