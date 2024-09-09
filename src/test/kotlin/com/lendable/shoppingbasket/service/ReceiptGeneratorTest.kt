package com.lendable.shoppingbasket.service

import com.lendable.shoppingbasket.model.BasketItem
import com.lendable.shoppingbasket.utils.aBasketItem
import com.lendable.shoppingbasket.utils.aOffer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReceiptGeneratorTest {

    @Test
    fun `Given a valid offer on bread and a cart containing bread, the receipt should contain the associated discount`() {
        //Given
        val item = aBasketItem("Bread", 1.0)
        val breadOffer = aOffer(item, 2, 0.5)
        //When
        val items = mapOf(item to 6)
        val offers = setOf(breadOffer)

        val receipt = ReceiptGenerator.getReceipt(items, offers)

        //Then
        assertEquals(6.0, receipt.totalWithoutDiscount)
        assertEquals(1.5, receipt.totalDiscount)
        assertEquals(4.5, receipt.totalPriceWithDiscount)

        assertEquals("""
                                🛒 LENDABLE SHOP RECEIPT 🛒               
                    ======================================================
                    Item          Quantity    Price        Total          
                    ------------------------------------------------------
                    Bread         6          £1.00          £6.00              
                    ------------------------------------------------------
                    Subtotal:                               £6.00               
                    🎉 Offer: Buy 2, get £0.50 off
                       on Bread             items disc.     -£1.50     
                    ------------------------------------------------------
                    Total discount:                         -£1.50              
                    ------------------------------------------------------
                    💰 FINAL TOTAL:                          £4.50              
                    ======================================================
                                    Thank you for shopping with us!       
                                        🌟 Have a great day! 🌟           
                    ------------------------------------------------------
                    You were served by: James Millner
                    We value your feedback! Please visit:
                    https://lendable.com/feedback""".trimIndent(),
            receipt.stringifiedReceipt.trimIndent())

    }

    @Test
    fun `Given a valid offer on bread and a cart containing cornflakes, the receipt should not contain a discount`() {
        //Given
        val item = aBasketItem("Cornflakes", 2.99)
        //When
        val items = mapOf(item to 1)

        val receipt = ReceiptGenerator.getReceipt(items, emptySet())
        println(receipt.stringifiedReceipt)

        //Then
        assertEquals(2.99, receipt.totalWithoutDiscount)
        assertEquals(0.0, receipt.totalDiscount)
        assertEquals(2.99, receipt.totalPriceWithDiscount)

        assertEquals("""            
                        🛒 LENDABLE SHOP RECEIPT 🛒               
            ======================================================
            Item          Quantity    Price        Total          
            ------------------------------------------------------
            Cornflakes    1          £2.99          £2.99              
            ------------------------------------------------------
            Subtotal:                               £2.99               
            ------------------------------------------------------
            💰 FINAL TOTAL:                          ${'$'}2.99              
            ======================================================
                            Thank you for shopping with us!       
                                🌟 Have a great day! 🌟           
            ------------------------------------------------------
            You were served by: James Millner
            We value your feedback! Please visit:
            https://lendable.com/feedback""".trimIndent(),
            receipt.stringifiedReceipt.trimIndent())
    }

    @Test
    fun `Given more than one valid offer and a cart containing eligible items, the receipt should contain the associated discounts`() {
        //Given
        val bread = aBasketItem("Bread", 1.0)
        val cornflakes = aBasketItem("Cornflakes", 2.99)

        val breadOffer = aOffer(bread, 2, 0.5)
        val cornflakesOffer = aOffer(cornflakes, 2, 1.5)

        //when
        val items = mapOf(bread to 2, cornflakes to 2)
        val offers = setOf(breadOffer, cornflakesOffer)

        val receipt = ReceiptGenerator.getReceipt(items, offers)

        //Then
        assertEquals(7.98, receipt.totalWithoutDiscount)
        assertEquals(2.0, receipt.totalDiscount)
        assertEquals(5.98, receipt.totalPriceWithDiscount)

        assertEquals("""            
                        🛒 LENDABLE SHOP RECEIPT 🛒               
            ======================================================
            Item          Quantity    Price        Total          
            ------------------------------------------------------
            Bread         2          £1.00          £2.00              
            Cornflakes    2          £2.99          £5.98              
            ------------------------------------------------------
            Subtotal:                               £7.98               
            🎉 Offer: Buy 2, get £0.50 off
               on Bread             items disc.     -£0.50     
            🎉 Offer: Buy 2, get £1.50 off
               on Cornflakes        items disc.     -£1.50     
            ------------------------------------------------------
            Total discount:                         -£2.00              
            ------------------------------------------------------
            💰 FINAL TOTAL:                          £5.98              
            ======================================================
                            Thank you for shopping with us!       
                                🌟 Have a great day! 🌟           
            ------------------------------------------------------
            You were served by: James Millner
            We value your feedback! Please visit:
            https://lendable.com/feedback""".trimIndent(),
            receipt.stringifiedReceipt.trimIndent())
    }
}