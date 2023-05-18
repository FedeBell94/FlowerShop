package service

import model.Bundle
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import service.FlowerShopService.findBestOrder
import java.math.BigDecimal.ZERO
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FlowerShopServiceTest {
    private val b0 = Bundle(0, ZERO)
    private val b1 = Bundle(1, ZERO)
    private val b2 = Bundle(2, ZERO)
    private val b3 = Bundle(3, ZERO)
    private val b5 = Bundle(5, ZERO)
    private val b6 = Bundle(6, ZERO)
    private val b9 = Bundle(9, ZERO)
    private val b10 = Bundle(10, ZERO)
    private val b100 = Bundle(100, ZERO)

    @Test
    fun `FlowerShopService findBestOrder works 1`() {
        // given
        val productBundles = setOf(b3, b10)

        // when
        val order = findBestOrder(10, productBundles)

        // then
        assertEquals(10, order.quantity)
        order.bundles.assertEquals(b10)
    }

    @Test
    fun `FlowerShopService findBestOrder works 2`() {
        // given
        val productBundles = setOf(b3, b6, b9)

        // when
        val order = findBestOrder(15, productBundles)

        // then
        assertEquals(15, order.quantity)
        order.bundles.assertEquals(b6, b9)
    }

    @Test
    fun `FlowerShopService findBestOrder works 3`() {
        // given
        val productBundles = setOf(b3, b5, b9)

        // when
        val order = findBestOrder(13, productBundles)

        // then
        assertEquals(13, order.quantity)
        order.bundles.assertEquals(b3, b5, b5)
    }

    @Test
    fun `FlowerShopService findBestOrder works 4`() {
        // given
        val productBundles = setOf(b1, b100)

        // when
        val order = findBestOrder(99, productBundles)

        // then
        assertEquals(99, order.quantity)
        order.bundles.assertEquals(*(0 until 99).map { b1 }.toTypedArray())
    }

    @Test
    fun `FlowerShopService findBestOrder works 5`() {
        // given
        val productBundles = setOf(b1, b100)

        // when
        val order = findBestOrder(100, productBundles)

        // then
        assertEquals(100, order.quantity)
        order.bundles.assertEquals(b100)
    }

    @Test
    fun `FlowerShopService findBestOrder works 6`() {
        // given
        val productBundles = setOf(b1, b100)

        // when
        val order = findBestOrder(101, productBundles)

        // then
        assertEquals(101, order.quantity)
        order.bundles.assertEquals(b1, b100)
    }

    @Test
    fun `FlowerShopService findBestOrder works 7`() {
        // given
        val productBundles = setOf(b1, b2, b3)

        // when
        val order = findBestOrder(7, productBundles)

        // then
        assertEquals(7, order.quantity)
        order.bundles.assertEquals(b1, b3, b3)
    }

    @Test
    fun `FlowerShopService findBestOrder works 8`() {
        // given
        val productBundles = setOf(b2, b5)

        // when
        val order = findBestOrder(11, productBundles)

        // then
        assertEquals(11, order.quantity)
        order.bundles.assertEquals(b2, b2, b2, b5)
    }

    @Test
    fun `FlowerShopService findBestOrder works 9`() {
        // given
        val productBundles = setOf(b2, b3)

        // when
        val order = findBestOrder(13, productBundles)

        // then
        assertEquals(13, order.quantity)
        order.bundles.assertEquals(b2, b2, b3, b3, b3)
    }

    @Test
    fun `FlowerShopService findBestOrder works 10`() {
        // given
        val productBundles = setOf(b5)

        // when
        val order = findBestOrder(3, productBundles)

        // then
        assertEquals(0, order.quantity)
        assertTrue(order.bundles.isEmpty())
    }

    @Test
    fun `FlowerShopService findBestOrder fails 1`() {
        assertThrows<IllegalArgumentException> {
            findBestOrder(0, setOf(b1))
        }
    }

    @Test
    fun `FlowerShopService findBestOrder fails 2`() {
        assertThrows<IllegalArgumentException> {
            findBestOrder(-1, setOf(b1))
        }
    }

    @Test
    fun `FlowerShopService findBestOrder fails 3`() {
        assertThrows<IllegalArgumentException> {
            findBestOrder(10, setOf(b1, b0))
        }
    }

    private fun List<Bundle>.assertEquals(vararg bundles: Bundle) =
        assertEquals(bundles.asList().sortedBy { it.quantity }, this.sortedBy { it.quantity })
}
