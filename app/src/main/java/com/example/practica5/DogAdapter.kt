package com.example.practica5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

// Definición de una clase llamada DogAdapter que extiende ArrayAdapter<String>
class DogAdapter(context: Context, private val dataList: List<String>) :
    ArrayAdapter<String>(context, R.layout.elemento_lista, dataList) {

    // Override de la función getView para personalizar la visualización de elementos en la lista
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val holder: ViewHolder

        // Verifica si la vista de elemento está siendo reutilizada o se crea una nueva
        if (itemView == null) {
            val inflater = LayoutInflater.from(context)
            itemView = inflater.inflate(R.layout.elemento_lista, parent, false)
            holder = ViewHolder()
            holder.dogImageView = itemView.findViewById(R.id.imageViewDog)
            itemView.tag = holder
        } else {
            holder = itemView.tag as ViewHolder
        }

        val imageUrl = dataList[position]

        // Utiliza la biblioteca Picasso para cargar y mostrar imágenes desde la URL
        Picasso.get().load(imageUrl).into(holder.dogImageView)

        return itemView!!
    }

    // Clase interna ViewHolder para mantener una referencia al ImageView de la vista de elemento
    private inner class ViewHolder {
        lateinit var dogImageView: ImageView
    }
}
