package com.example.nilocursofirebase.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nilocursofirebase.databinding.FragmentTrackBinding
import com.example.nilocursofirebase.entities.Order
import com.example.nilocursofirebase.order.OrderAux

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
}