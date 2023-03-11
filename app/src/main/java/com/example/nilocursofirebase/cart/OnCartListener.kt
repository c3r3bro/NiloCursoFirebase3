package com.example.nilocursofirebase.cart

import com.example.nilocursofirebase.entities.Product

interface OnCartListener {
    fun setQuantity(product: Product)
    fun showTotal(total: Double)

}