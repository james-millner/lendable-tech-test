package com.lendable.shoppingbasket.utils

import com.lendable.shoppingbasket.model.BasketItem
import com.lendable.shoppingbasket.model.Offer

fun aBasketItem(name: String = "Bread", price: Double = 1.0) = BasketItem(name, price)

fun aOffer(basketItem: BasketItem = aBasketItem(), quantity: Int = 2, discountAmount: Double = 0.5) = Offer(
    basketItem,
    quantity,
    discountAmount
)