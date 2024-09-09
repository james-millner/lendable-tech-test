package com.lendable.shoppingbasket.service

import com.lendable.shoppingbasket.model.BasketItem
import com.lendable.shoppingbasket.model.Offer
import com.lendable.shoppingbasket.model.Receipt
import org.springframework.stereotype.Component

/**
 * Represents the V1 implementation of the shopping cart.
 * @see ShoppingCart
 * @property offers the set of offers available
 */
@Component
class ShoppingCartV1(val offers: Set<Offer>): ShoppingCart {

    private val items = mutableMapOf<BasketItem, Int>()

    /**
     * Get the items in the cart
     * @return the items in the cart
     */
    override fun getItems(): Map<BasketItem, Int> {
        return items
    }

    /**
     * Add an item to the cart
     */
    override fun addItem(item: BasketItem, quantity: Int) {
        if (quantity == 0) {
            throw IllegalArgumentException("Quantity must be greater than 0")
        }
        items[item] = items.getOrDefault(item, 0) + quantity
    }

    /**
     * Remove an item from the cart
     */
    override fun removeItem(item: BasketItem, quantity: Int) {
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

    /**
     * Get the receipt for the items in the cart
     * @return the receipt
     * @see ReceiptGenerator
     * @see Receipt
     */
    override fun getItemisedReceipt(): Receipt = ReceiptGenerator
            .getReceipt(
                items,
                offers
            )
}