package com.example.nilocursofirebase.chat

import com.example.nilocursofirebase.entities.Message

interface OnChatListener {
    fun deleteMessage(message: Message)
}