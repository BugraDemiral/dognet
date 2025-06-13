package com.monomobile.dognet.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): BreedListResponse

    @GET("breed/{breed}/images/random/10")
    suspend fun getBreedImages(@Path("breed") breed: String): BreedImagesResponse
}
