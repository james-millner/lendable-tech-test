package com.lendable.shoppingbasket.service

import com.lendable.shoppingbasket.model.BasketItem
import com.lendable.shoppingbasket.model.Offer
import com.lendable.shoppingbasket.model.Receipt
import java.math.RoundingMode

class ReceiptGenerator {

    companion object {
        fun getReceipt(
            items: Map<BasketItem, Int>,
            offers: Set<Offer>
        ): Receipt {
            val sb = StringBuilder()
            sb.appendLine("            🛒 LENDABLE SHOP RECEIPT 🛒               ")
            sb.appendLine("======================================================")
            sb.appendLine("Item          Quantity    Price        Total          ")
            sb.appendLine("------------------------------------------------------")

            val total = items.entries.sumOf { (item, quantity) ->
                val itemTotal = item.price * quantity
                sb.appendLine("%-13s %-10d £%-13.2f £%-18.2f".format(item.name, quantity, item.price, itemTotal))

                itemTotal
            }

            sb.appendLine("------------------------------------------------------")
            sb.appendLine("Subtotal:                               £%-19.2f".format(total))

            val totalPriceReduction = offers.sumOf { offer ->
                val cartItemQuantity = items[offer.item] ?: 0

                val discountAmount = offer.calculateDiscountAmount(cartItemQuantity)

                if (discountAmount > 0) {
                    sb.appendLine("🎉 Offer: Buy ${offer.quantity}, get £${String.format("%s", formatPrice(offer.discount))} off")
                    sb.appendLine("   on %-13s     items disc.     -£%-9s".format(offer.item.name, formatPrice(discountAmount)))
                }

                discountAmount
            }

            if (totalPriceReduction > 0) {
                sb.appendLine("------------------------------------------------------")
                sb.appendLine("Total discount:                         -£%-18s".format(formatPrice(totalPriceReduction)))
                sb.appendLine("------------------------------------------------------")
                sb.appendLine("💰 FINAL TOTAL:                          £%-18s".format(formatPrice(total - totalPriceReduction)))
            } else {
                sb.appendLine("------------------------------------------------------")
                sb.appendLine("💰 FINAL TOTAL:                          $%-18s".format(formatPrice(total)))
            }

            sb.appendLine("======================================================")
            sb.appendLine("                Thank you for shopping with us!       ")
            sb.appendLine("                    🌟 Have a great day! 🌟           ")
            sb.appendLine("------------------------------------------------------")
            sb.appendLine("You were served by: James Millner")
            sb.appendLine("We value your feedback! Please visit:")
            sb.appendLine("https://lendable.com/feedback")

            return Receipt(
                totalWithoutDiscount = total,
                totalPriceWithDiscount =  total - totalPriceReduction,
                totalDiscount = totalPriceReduction,
                stringifiedReceipt = sb.toString()
            )
        }

        private fun formatPrice(price: Double): String {
            return "%s".format(price.toBigDecimal().setScale(2, RoundingMode.FLOOR))
        }
    }
}