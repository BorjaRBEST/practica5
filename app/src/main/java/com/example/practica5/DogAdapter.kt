import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.practica5.R
import com.squareup.picasso.Picasso

class DogAdapter(context: Context, private val dataList: List<String>) :
    ArrayAdapter<String>(context, R.layout.elemento_lista, dataList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val holder: ViewHolder

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

        // Utiliza Picasso u otra biblioteca para cargar y mostrar imágenes desde la URL
        Picasso.get().load(imageUrl).into(holder.dogImageView)

        return itemView!!
    }

    private inner class ViewHolder {
        internal lateinit var dogImageView: ImageView
    }
}
