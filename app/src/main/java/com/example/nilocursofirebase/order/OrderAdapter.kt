package com.example.nilocursofirebase.order

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nilocursofirebase.R
import com.example.nilocursofirebase.databinding.ItemOrderBinding
import com.example.nilocursofirebase.entities.Order

class OrderAdapter(
    private val orderList: MutableList<Order>,
    private val listener: OnOrderListener
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orderList[position]
        holder.setListener(order)

        holder.binding.tvId.text = context.getString(R.string.order_id, order.id)
        var names = ""
        order.products.forEach{
            names += "${it.value.name}, "
        }
        holder.binding.tvProductNames.text = names.dropLast(2) // dropLast elimina los ultimos 2 caracteres de la var names
        holder.binding.tvTotalPrice.text = context.getString(R.string.product_full_cart, order.totalPrice)
        holder.binding.tvStatus.text = context.getString(R.string.order_status, "en espera")
    }

    fun add(order: Order){
        orderList.add(order)
        notifyItemInserted(orderList.size-1)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemOrderBinding.bind(view)

        fun setListener(order: Order) {
            binding.btnTrack.setOnClickListener {
                listener.onTrack(order)
            }
            binding.chpChat.setOnClickListener {
                listener.onStartChat(order)
            }
        }
    }


}