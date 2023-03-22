package com.example.nilocursofirebase.track

import android.os.Bundle
import com.example.nilocursofirebase.Constants
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nilocursofirebase.R
import com.example.nilocursofirebase.databinding.FragmentTrackBinding
import com.example.nilocursofirebase.entities.Order
import com.example.nilocursofirebase.order.OrderAux
import com.google.firebase.firestore.FirebaseFirestore

class TrackFragment: Fragment() {
    private var binding: FragmentTrackBinding? = null
    private var order: Order? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackBinding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getOrder()
    }

    private fun getOrder(){
        order = (activity as? OrderAux)?.getOrderSelected()

        order?.let {
            updateUI(it)

            getOrderInRealtime(it.id)

            setupActionBar()
        }
    }

    private fun updateUI(order: Order){
        binding?.let {
            it.progressBar.progress = order.status*(100/3)-15
            it.cbOrdered.isChecked = order.status > 0
            it.cbPreparing.isChecked= order.status > 1
            it.cbSent.isChecked= order.status > 2
            it.cbODelivered.isChecked= order.status > 3
        }
    }

    private fun getOrderInRealtime(orderId: String){
        val db = FirebaseFirestore.getInstance()

        val orderRef = db.collection(Constants.COLL_REQUESTS).document(orderId)
        orderRef.addSnapshotListener { snapshot, error ->
            if (error != null){
                Toast.makeText(activity, "Error al consultar esta orden", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()){
                val order = snapshot.toObject(Order::class.java)
                order?.let {
                    it.id = snapshot.id

                    updateUI(it)
                }
            }
        }
    }

    private fun setupActionBar(){
        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.supportActionBar?.title = getString(R.string.track_title)
            setHasOptionsMenu(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            it.supportActionBar?.title = getString(R.string.order_title)
            setHasOptionsMenu(false)
        }
        super.onDestroy()
    }
}