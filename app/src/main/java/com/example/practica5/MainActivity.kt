package com.example.practica5

import DogAdapter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.practica5.API.APIService
import com.example.practica5.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var etBreed: EditText
    private lateinit var btnSearch: Button
    private lateinit var lvDogs: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBreed = findViewById(R.id.etBreed)
        btnSearch = findViewById(R.id.btnSearch)
        lvDogs = findViewById(R.id.lvDogs)

        btnSearch.setOnClickListener {
            val breed = etBreed.text.toString()
            if (breed.isNotBlank()) {
                searchByName(breed)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            val dogImages: MutableList<String> = mutableListOf()

            runOnUiThread {
                if (call.isSuccessful) {
                    val images = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)

                    val adapter = DogAdapter(this@MainActivity, dogImages)
                    lvDogs.adapter = adapter
                    lvDogs.visibility = View.VISIBLE
                } else {
                    // Mostrar error
                    // ...
                }
            }
        }
    }
}
