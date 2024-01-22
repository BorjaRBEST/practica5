package com.example.practica5

import com.google.gson.annotations.SerializedName

// Definición de una clase de datos (data class) llamada DogsResponse
data class DogsResponse (
    @SerializedName("status") var status: String,  // Atributo para almacenar el estado de la respuesta
    @SerializedName("message") var images: List<String>  // Atributo para almacenar una lista de imágenes
)
