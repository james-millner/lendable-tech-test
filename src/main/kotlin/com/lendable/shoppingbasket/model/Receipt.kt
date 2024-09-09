package com.lendable.shoppingbasket.model

data class Receipt(val totalWithoutDiscount: Double, val totalPriceWithDiscount: Double, val totalDiscount: Double, val stringifiedReceipt: String)