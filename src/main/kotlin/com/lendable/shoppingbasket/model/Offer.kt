package com.lendable.shoppingbasket.model

import kotlin.math.floor

data class Offer(val item: BasketItem, val quantity: Int, val discount: Double) {

    fun calculateDiscountAmount(cartQuantity: Int): Double {
        val numberOfOfferApplications = floor(cartQuantity.toDouble() / quantity).toInt()
        return numberOfOfferApplications * discount
    }

}
