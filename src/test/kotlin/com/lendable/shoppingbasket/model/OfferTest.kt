package com.lendable.shoppingbasket.model

import com.lendable.shoppingbasket.utils.aBasketItem
import com.lendable.shoppingbasket.utils.aOffer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OfferTest {

    @Test
    fun `Given an offer and a quantity of 6, ensure offer applies the correct discount`() {
        //Given
        val offer = aOffer()

        //When
        val discount = offer.calculateDiscountAmount(6)

        //Then
        assertEquals(1.5, discount)
    }

    @Test
    fun `Given an offer and quantity of 5, ensure offer applies the correct discount`() {
        //Given
        val offer = aOffer()

        //When
        val discount = offer.calculateDiscountAmount(5)

        //Then
        assertEquals(1.0, discount)
    }

    @Test
    fun `Given an offer no items, ensure offer applies the correct discount`() {
        //Given
        val item = BasketItem("Bread", 1.0)
        val offer = Offer(item, 2, 0.5)

        //When
        val discount = offer.calculateDiscountAmount(0)

        //Then
        assertEquals(0.0, discount)
    }
}