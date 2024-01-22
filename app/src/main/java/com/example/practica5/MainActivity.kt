import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.practica5.API.APIService
import com.example.practica5.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llama a la función searchByName con el nombre de raza de perro que desees mostrar
        searchByName("labrador")
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
                    val listView = findViewById<ListView>(R.id.lvDogs)

                    // Configura el adaptador para el ListView
                    listView.adapter = adapter

                    // Muestra el ListView
                    listView.visibility = View.VISIBLE
                } else {
                    // Muestra error
                    // ...
                }
            }
        }
    }
}
