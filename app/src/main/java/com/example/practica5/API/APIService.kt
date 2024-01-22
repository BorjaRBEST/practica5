package com.example.practica5.API

import com.example.practica5.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

// Definición de una interfaz llamada APIService que declara métodos para realizar solicitudes HTTP
interface APIService {

    // Anotación @GET para especificar que se trata de una solicitud GET
    // La URL se proporciona dinámicamente como parámetro con la anotación @Url
    @GET
    suspend fun getDogsByBreeds(@Url url: String): Response<DogsResponse>
}
