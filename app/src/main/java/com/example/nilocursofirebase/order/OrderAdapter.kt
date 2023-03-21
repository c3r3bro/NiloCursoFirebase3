package com.example.nilocursofirebase.order

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nilocursofirebase.databinding.ItemOrderBinding
import com.example.nilocursofirebase.entities.Order

class OrderAdapter (private val orderList: MutableList<Order>, private val listener: OnOrderListener){


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemOrderBinding.bind(view)

        fun setListener(order: Order){
            binding.btnTrack.setOnClickListener {
                listener.onTrack(order)
            }
            binding.chpChat.setOnClickListener {
                listener.onStartChat(order)
            }
        }
    }
}