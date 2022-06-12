package com.nonbinsys.greenersapp.paquete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nonbinsys.greenersapp.databinding.PaqueteItemBinding

class PaquetesAdapter: RecyclerView.Adapter<PaquetesAdapter.PaqueteViewHolder>(){
    inner class PaqueteViewHolder(val binding: PaqueteItemBinding): RecyclerView.ViewHolder(binding.root)

    private var paquetesList = ArrayList<Paquete>()
    var onItemClick: ((Paquete) -> Unit)? = null

    fun setPaquetesList(paquetesList: List<Paquete>) {
        this.paquetesList = paquetesList as ArrayList<Paquete>
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