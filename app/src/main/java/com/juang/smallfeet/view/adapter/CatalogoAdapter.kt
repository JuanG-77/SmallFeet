package com.juang.smallfeet.view.adapter

import android.content.ClipData
import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.juang.smallfeet.R
import com.juang.smallfeet.model.zapatos
import com.squareup.picasso.Picasso

class CatalogoAdapter(private val context: Context,var clickListener: OnBookItemClickListener): RecyclerView.Adapter<CatalogoAdapter.ViewHolder>() {

    private var zapatoslista = mutableListOf<zapatos>()

    fun setListData(data: MutableList<zapatos>){
        zapatoslista = data
    }

    inner class ViewHolder(ItemView:View): RecyclerView.ViewHolder(ItemView){
       fun binWew(zapato: zapatos, action:OnBookItemClickListener){
           itemView.findViewById<TextView>(R.id.Title).text = zapato.titulo
           itemView.findViewById<TextView>(R.id.precio).text = zapato.precio
           itemView.findViewById<TextView>(R.id.Tallas).text = zapato.tallas
           Picasso.with(context).load(zapato.imagen).into(itemView.findViewById<ImageView>(R.id.image))
           val btnshopping=itemView.findViewById<ImageButton>(R.id.shopping)
           btnshopping.setOnClickListener {
               action.onItemClick(zapato,adapterPosition)
           }
       }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val zapato = zapatoslista[i]
        viewHolder.binWew(zapato,clickListener)
    }

    override fun getItemCount(): Int {
        return if(zapatoslista.size > 0){
            zapatoslista.size
        }
        else{
            0
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_catalogo,viewGroup, false)
        return ViewHolder(v)
    }
}


interface OnBookItemClickListener{
    fun onItemClick(zatato: zapatos,position:Int)
}

