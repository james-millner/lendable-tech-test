package com.lendable.shoppingbasket.model

import kotlin.math.floor

/**
 * Represents an offer on a basket item
 * @property item the item the offer applies to
 * @property quantity the quantity of items required to apply the offer
 * @property discount the discount amount to apply
 *
 */
data class Offer(val item: BasketItem, val quantity: Int, val discount: Double) {

    /**
     * Calculate the discount amount for the given quantity of items
     * @param cartQuantity the quantity of items in the cart
     * @return the discount amount
     */
    fun calculateDiscountAmount(cartQuantity: Int): Double {
        val numberOfOfferApplications = floor(cartQuantity.toDouble() / quantity).toInt()
        return numberOfOfferApplications * discount
    }

}
