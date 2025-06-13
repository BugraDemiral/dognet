package com.monomobile.dognet.data.remote

data class BreedListResponse(
    val message: Map<String, List<String>>,
    val status: String
)
