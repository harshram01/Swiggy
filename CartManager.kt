package com.example.swiftbite

object CartManager {

    val cartItems = mutableListOf<CartItem>()

    fun addToCart(item: CartItem) {
        val existingItem = cartItems.find { it.name == item.name }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(item.copy())
        }
    }

    fun increaseQuantity(item: CartItem) {
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            existingItem.quantity++
        }
    }

    fun decreaseQuantity(item: CartItem) {
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                cartItems.remove(existingItem)
            }
        }
    }

    fun getTotalPrice(): Int {
        var total = 0
        for (item in cartItems) {
            total += item.price * item.quantity
        }
        return total
    }
}