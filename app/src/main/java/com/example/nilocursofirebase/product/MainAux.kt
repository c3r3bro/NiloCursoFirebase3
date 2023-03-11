package com.example.nilocursofirebase.product

import com.example.nilocursofirebase.entities.Product

interface MainAux {
    fun getProdcutsCart(): MutableList<Product>
    fun updateTotal()
    fun clearCart()

    fun gerProductSelected(): Product?
    fun showButton(isVisible: Boolean)
    fun addProductToCart(product: Product)

}