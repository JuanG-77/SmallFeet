package com.juang.smallfeet.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juang.smallfeet.R
import com.juang.smallfeet.model.compras
import com.squareup.picasso.Picasso

class ComprasAdapter(private val context: Context, var clickListener: OnCompraItemClickListener): RecyclerView.Adapter<ComprasAdapter.ViewHolder>() {

    private var zapatoslista = mutableListOf<compras>()

    fun setListData(data: MutableList<compras>){
        zapatoslista = data
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        fun binWew(zapato: compras, action:OnCompraItemClickListener){
            itemView.findViewById<TextView>(R.id.Title).text = zapato.titulo
            itemView.findViewById<TextView>(R.id.precio).text = zapato.precio
            itemView.findViewById<TextView>(R.id.Tallas).text = zapato.tallas
            Picasso.with(context).load(zapato.imagen).into(itemView.findViewById<ImageView>(R.id.image))
            val btneliminar=itemView.findViewById<ImageButton>(R.id.eliminar)
            btneliminar.setOnClickListener {
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
        val v= LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_compras,viewGroup, false)
        return ViewHolder(v)
    }
}
interface OnCompraItemClickListener{
    fun onItemClick(zatato: compras, position:Int)

}
