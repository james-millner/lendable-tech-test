package com.lendable.shoppingbasket.service

import com.lendable.shoppingbasket.model.BasketItem
import com.lendable.shoppingbasket.model.Receipt

/**
 * Represents a shopping cart and the required operations
 */
interface ShoppingCart {

    fun addItem(item: BasketItem, quantity: Int)

    fun removeItem(item: BasketItem, quantity: Int)

    fun getItems(): Map<BasketItem, Int>

    fun getItemisedReceipt(): Receipt
}