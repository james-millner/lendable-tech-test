package com.lendable.shoppingbasket.service

import com.lendable.shoppingbasket.model.BasketItem
import com.lendable.shoppingbasket.model.Offer
import com.lendable.shoppingbasket.model.Receipt
import org.springframework.stereotype.Component

@Component
class ShoppingCart(val offers: Set<Offer>) {

    private val items = mutableMapOf<BasketItem, Int>()

    fun getItems(): Map<BasketItem, Int> {
        return items
    }

    fun addItem(item: BasketItem, quantity: Int = 1) {
        items[item] = items.getOrDefault(item, 0) + quantity
    }

    fun removeItem(item: BasketItem, quantity: Int = 1) {
        // If the item is not in the cart, do nothing
        val currentQuantity = items[item] ?: return

        // If the quantity to remove is greater than the current quantity, remove the item from the cart
        // Else deduct the quantity from the cart
        if (currentQuantity - quantity <= 0) {
            items.remove(item)
        } else {
            items[item] = currentQuantity - quantity
        }
    }

    fun getItemisedReceipt(): Receipt = ReceiptGenerator
            .getReceipt(
                items,
                offers
            )
}