package com.nonbinsys.greenersapp.carrito

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nonbinsys.greenersapp.databinding.CarritoItemBinding
import com.nonbinsys.greenersapp.paquete.PaqueteCarrito

class CarritoAdapter: RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {
    inner class CarritoViewHolder(val binding: CarritoItemBinding): RecyclerView.ViewHolder(binding.root)

    private var carritoList = ArrayList<PaqueteCarrito>()
    var onItemClick: ((PaqueteCarrito) -> Unit)? = null

    fun setCarritosList(carritosList: List<PaqueteCarrito>) {
        this.carritoList = carritosList as ArrayList<PaqueteCarrito>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        return CarritoViewHolder(
            CarritoItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        //Glide
        holder.binding.tvNombrePaquete.text = carritoList[position].nombre
        holder.binding.tvCantidad.text = "Cantidad: " + carritoList[position].cantidad.toString()
        holder.binding.tvPrecio.text = "Precio: " + carritoList[position].precio.toString()
    }

    override fun getItemCount(): Int {
        return carritoList.size
    }


}