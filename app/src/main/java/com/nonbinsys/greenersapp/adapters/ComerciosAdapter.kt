package com.nonbinsys.greenersapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nonbinsys.greenersapp.data.pojo.Comercio
import com.nonbinsys.greenersapp.databinding.ComercioItemBinding

class ComerciosAdapter: RecyclerView.Adapter<ComerciosAdapter.ComercioViewHolder>() {
    inner class ComercioViewHolder(val binding: ComercioItemBinding): RecyclerView.ViewHolder(binding.root)

    private var comerciosList = ArrayList<Comercio>()
    var onItemClick: ((Comercio) -> Unit)? = null

    fun setComerciosList(comerciosList: List<Comercio>){
        this.comerciosList = comerciosList as ArrayList<Comercio>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComercioViewHolder {
        return ComercioViewHolder(
            ComercioItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ComercioViewHolder, position: Int) {
        Glide.with(holder.itemView).load(comerciosList[position].link_logo).into(holder.binding.imgComercio)

        holder.binding.tvNombreComercio.text = comerciosList[position].nombre
        holder.binding.tvTelefono.text = comerciosList[position].telf
        holder.binding.tvDireccion.text = comerciosList[position].direccion

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(comerciosList[position])
        }
    }

    override fun getItemCount(): Int {
        return comerciosList.size
    }


}