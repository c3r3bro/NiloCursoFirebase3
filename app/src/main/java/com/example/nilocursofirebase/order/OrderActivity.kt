package com.example.nilocursofirebase.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nilocursofirebase.Constants
import com.example.nilocursofirebase.R
import com.example.nilocursofirebase.databinding.ActivityOrderBinding
import com.example.nilocursofirebase.entities.Order
import com.google.firebase.firestore.FirebaseFirestore

class OrderActivity : AppCompatActivity(), OnOrderListener {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var adapter: OrderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupFirestore()
    }

    private fun setupRecyclerView() {
        adapter = OrderAdapter(mutableListOf(), this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@OrderActivity)
            adapter = this@OrderActivity.adapter
        }
    }

    private fun setupFirestore(){
        val db = FirebaseFirestore.getInstance()

        db.collection(Constants.COLL_REQUESTS)
            .get()
            .addOnSuccessListener {
                for (document in it){
                    val order = document.toObject(Order::class.java)
                    order.id = document.id
                    adapter.add(order)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al consultar los datos", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onTrack(order: Order) {
        TODO("Not yet implemented")
    }

    override fun onStartChat(order: Order) {
        TODO("Not yet implemented")
    }
}