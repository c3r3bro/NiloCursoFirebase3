package com.example.nilocursofirebase.product

import com.example.nilocursofirebase.entities.Product

interface OnProductListener {
    fun onClick(product: Product)
}