package com.example.practica5.API

import com.example.practica5.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreeds(@Url url: String): Response<DogsResponse>
}