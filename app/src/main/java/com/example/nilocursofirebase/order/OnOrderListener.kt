package com.example.nilocursofirebase.order

import com.example.nilocursofirebase.entities.Order

interface OnOrderListener {
    fun onTrack(order: Order)
    fun onStartChat(order: Order)
}