package com.lendable.shoppingbasket.model

/**
 * Represents a receipt for a shopping basket
 * @property totalWithoutDiscount the total price of the basket without any discounts applied
 * @property totalPriceWithDiscount the total price of the basket with discounts applied
 * @property totalDiscount the total discount amount applied to the basket
 * @property stringifiedReceipt a string representation of the receipt
 */
data class Receipt(val totalWithoutDiscount: Double, val totalPriceWithDiscount: Double, val totalDiscount: Double, val stringifiedReceipt: String)