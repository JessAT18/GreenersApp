package com.nonbinsys.greenersapp.paquete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nonbinsys.greenersapp.databinding.PaqueteItemBinding

class PaquetesInventarioAdapter: RecyclerView.Adapter<PaquetesInventarioAdapter.PaqueteViewHolder>(){
    inner class PaqueteViewHolder(val binding: PaqueteItemBinding): RecyclerView.ViewHolder(binding.root)

    private var paquetesList = ArrayList<PaqueteInventario>()
    var onItemClick: ((PaqueteInventario) -> Unit)? = null

    fun setPaquetesList(paquetesList: List<PaqueteInventario>) {
        this.paquetesList = paquetesList as ArrayList<PaqueteInventario>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaqueteViewHolder {
        return PaqueteViewHolder(
            PaqueteItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PaqueteViewHolder, position: Int) {
        Glide.with(holder.itemView).load(paquetesList[position].link_paquete).into(holder.binding.imgPaquete)

        holder.binding.tvNombrePaquete.text = paquetesList[position].nombre
        holder.binding.tvDescripcion.text = paquetesList[position].descripcion
        holder.binding.tvPrecio.text = "Bs " + paquetesList[position].precio.toString()

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(paquetesList[position])
        }
    }

    override fun getItemCount(): Int {
        return paquetesList.size
    }
}