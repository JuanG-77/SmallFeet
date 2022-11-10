package com.juang.smallfeet.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.juang.smallfeet.R

class CatalogoAdapter: RecyclerView.Adapter<CatalogoAdapter.ViewHolder>() {



    inner class ViewHolder(ItemView:View): RecyclerView.ViewHolder(ItemView){
        var itemimage: ImageView
        var itemTitle: TextView
        var itemprecio: TextView
        var itemTallas: TextView

        init {
            itemimage=ItemView.findViewById(R.id.image)
            itemTitle=ItemView.findViewById(R.id.Title)
            itemprecio=ItemView.findViewById(R.id.precio)
            itemTallas=ItemView.findViewById(R.id.Tallas)
        }
    }
    val titles= arrayOf("Zapatos Unicornio", "Zapatos Rosa", "Zapatos Alumbrados", "Capitan America", "Zapatos Cars", "A la moda")
    val precio= arrayOf("90.000", "120.000", "160.000", "130.000", "90.000", "120.000")
    val Tallas= arrayOf("19, 20, 22", "22, 24, 25", "19, 20, 26", "19, 20, 26", "19, 20, 22", "22, 24, 25")
    val image= arrayOf(R.drawable.primerproducto, R.drawable.segundoproducto, R.drawable.tercerproducto, R.drawable.cuartoproducto, R.drawable.quintoproducto, R.drawable.sextoproducto)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemprecio.text=precio[i]
        viewHolder.itemTallas.text=Tallas[i]
        viewHolder.itemimage.setImageResource(image[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_catalogo,viewGroup, false)
        return ViewHolder(v)
    }
}