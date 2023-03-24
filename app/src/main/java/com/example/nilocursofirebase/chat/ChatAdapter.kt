package com.example.nilocursofirebase.chat

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nilocursofirebase.databinding.ItemChatBinding
import com.example.nilocursofirebase.entities.Message

class ChatAdapter(
    private val messageList: MutableList<Message>,
    private val listener: OnChatListener
) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun add(message: Message) {
        if (!messageList.contains(message)) {
            messageList.add(message)
            notifyItemInserted(messageList.size-1)
        }
    }

    fun update(message: Message){
        val index = messageList.indexOf(message)
        if (index!=-1){
            messageList.set(index, message)
            notifyItemChanged(index)
        }
    }

    fun delete(message: Message){
        val index = messageList.indexOf(message)
        if (index!=-1){
            messageList.removeAt(index)
            notifyItemRemoved(index)
        }
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemChatBinding.bind(view)

        fun setListener(message: Message) {
            binding.tvMessage.setOnLongClickListener {
                listener.deleteMessage(message)
                true
            }
        }
    }


}