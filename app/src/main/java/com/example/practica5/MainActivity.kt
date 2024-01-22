package com.example.practica5

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.practica5.API.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // Declaración de variables para los elementos de la interfaz de usuario
    private lateinit var etBreed: EditText
    private lateinit var btnSearch: Button
    private lateinit var lvDogs: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de variables de elementos de la interfaz de usuario
        etBreed = findViewById(R.id.etBreed)
        btnSearch = findViewById(R.id.btnSearch)
        lvDogs = findViewById(R.id.lvDogs)

        // Configuración del evento clic en el botón de búsqueda
        btnSearch.setOnClickListener {
            val breed = etBreed.text.toString()
            if (breed.isNotBlank()) {
                // Llamar a la función de búsqueda cuando se hace clic en el botón
                searchByName(breed)
            }
        }
    }

    // Función para obtener una instancia de Retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Función para realizar la búsqueda de imágenes de perros por raza
    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            val dogImages: MutableList<String> = mutableListOf()

            runOnUiThread {
                if (call.isSuccessful) {
                    // Si la solicitud es exitosa, actualiza la lista de imágenes
                    val images = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)

                    val adapter = DogAdapter(this@MainActivity, dogImages)
                    lvDogs.adapter = adapter
                    lvDogs.visibility = View.VISIBLE
                } else {
                    // Aquí se puede manejar el caso en que la solicitud no sea exitosa
                    // Por ejemplo, mostrar un mensaje de error al usuario
                }
            }
        }
    }
}
