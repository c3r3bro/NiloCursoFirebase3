package com.example.nilocursofirebase.chat

import android.os.Bundle
import com.example.nilocursofirebase.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nilocursofirebase.databinding.FragmentChatBinding
import com.example.nilocursofirebase.entities.Message
import com.example.nilocursofirebase.entities.Order
import com.example.nilocursofirebase.order.OrderAux
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment(), OnChatListener {

    private var binding: FragmentChatBinding? = null

    private lateinit var adapter: ChatAdapter
    private var order: Order? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getOrder()

        setupRecyclerView()

        setupButtons()

    }

    private fun getOrder() {
        order = (activity as? OrderAux)?.getOrderSelected()
        order?.let {
            setupRealtimeDatabase()
        }
    }

    private fun setupRealtimeDatabase() {

    }

    private fun setupRecyclerView() {
        adapter = ChatAdapter(mutableListOf(), this)
        binding?.let {
            it.recyclerView.apply {
                layoutManager = LinearLayoutManager(context).also {
                    it.stackFromEnd = true
                }
                adapter = this@ChatFragment.adapter
            }
        }

       /* (1..20).forEach {
            adapter.add(
                Message(
                    it.toString(),
                    if (it % 4 == 0) "hola como estas?, hola como estas?, hola como estas?" else "hola como estas?",
                    if (it % 3 == 0) "tu" else "yo",
                    "yo"
                )
            )
        }*/
    }

    private fun setupButtons() {
        binding?.let { binding ->
            binding.ibSend.setOnClickListener {
                sendMessage()
            }
        }
    }

    private fun sendMessage() {
        binding?.let { binding ->
            order?.let {
                val database = Firebase.database
                val chatRef = database.getReference(Constants.PATH_CHATS).child(it.id)
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val message =
                        Message(message = binding.etMessage.text.toString().trim(), sender = it.uid)
                    binding.ibSend.isEnabled = false

                    chatRef.push().setValue(message)
                        .addOnSuccessListener {
                            binding.etMessage.setText("")
                        }
                        .addOnCompleteListener {
                            binding.ibSend.isEnabled = true
                        }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun deleteMessage(message: Message) {

    }
}