package com.lendable.shoppingbasket.service

import com.lendable.shoppingbasket.utils.aBasketItem
import com.lendable.shoppingbasket.utils.aOffer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ShoppingCartV1Test {

    private lateinit var cart: ShoppingCartV1

    @BeforeEach
    fun setup() {
        val offer = aOffer()
        cart = ShoppingCartV1(setOf(offer))
    }

    @Nested
    inner class BasketItemTests {

        @Test
        fun `adding an item to the cart should increase the quantity of that item`() {
            //Given
            val item = aBasketItem()

            //When
            cart.addItem(item, 1)

            //Then
            assertEquals(1, cart.getItems()[item])
        }

        @Test
        fun `removing an item from the cart that does exist should correct its quantity`() {

            //Given
            val item = aBasketItem()
            cart.addItem(item, 2)

            //When
            cart.removeItem(item, 1)

            //Then
            assertEquals(1, cart.getItems()[item])
        }

        @Test
        fun `removing an item from the cart that doesnt exist should return null for that item`() {

            //Given
            val item = aBasketItem()
            cart.addItem(item, 0)

            //When
            cart.removeItem(item, 1)

            //Then
            assertNull(cart.getItems()[item])
        }

        @Test
        fun `given a set of items, the total is correct for the cart`() {
            val item = aBasketItem("Banana", 1.50)
            cart.addItem(item, 1)

            val receipt = cart.getItemisedReceipt()

            assertEquals(0.0, receipt.totalDiscount)
            assertEquals(1.50, receipt.totalPriceWithDiscount)
            assertEquals(1.50, receipt.totalWithoutDiscount)
        }
    }
}