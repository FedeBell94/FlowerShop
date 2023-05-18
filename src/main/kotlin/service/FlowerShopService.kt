package service

import model.Bundle

object FlowerShopService {

    // Time: O(desiredQuantity * bundles)
    // Space: O(desiredQuantity)
    fun findBestOrder(desiredQuantity: Int, bundles: Set<Bundle>): Order {
        assert(desiredQuantity > 0) { IllegalArgumentException("Desired quantity must be positive") }
        assert(bundles.all { it.quantity > 0 }) { IllegalArgumentException("All bundle quantities must be positive") }

        val partialOrders = Array(desiredQuantity + 1) { Order() }

        for (currQuantity in 0 .. desiredQuantity) {
            var maxOrder = partialOrders[currQuantity]

            bundles.forEach { b ->
                if (b.quantity <= currQuantity) {
                    val baseOrder = partialOrders[currQuantity - b.quantity]
                    val currOrder = Order(
                        quantity = baseOrder.quantity + b.quantity,
                        bundles = baseOrder.bundles + b
                    )
                    if (currOrder.quantity > maxOrder.quantity ||
                        (currOrder.quantity == maxOrder.quantity && currOrder.bundles.size < maxOrder.bundles.size)) {
                        maxOrder = currOrder
                    }
                }
            }

            partialOrders[currQuantity] = maxOrder
        }

        return partialOrders.last()
    }

    private fun assert(value: Boolean, lazyException: () -> RuntimeException) {
        if (!value) throw lazyException.invoke()
    }
}

data class Order(
    val quantity: Int = 0,
    val bundles: List<Bundle> = emptyList(),
)
