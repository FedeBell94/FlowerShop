package service

import model.Product

object OrderPrinterService {
    const val IMPOSSIBLE_EXACT_SOLUTION_MESSAGE = "Impossible to fulfill the order with the given bundles. " +
        "The following is the closest solution"

    fun printFormatted(originalOrderQuantity: Int, product: Product, order: Order) {
        if (originalOrderQuantity != order.quantity) println(IMPOSSIBLE_EXACT_SOLUTION_MESSAGE)

        val totalPrice = order.bundles.sumOf { it.price }
        println("$originalOrderQuantity ${product.code} $${totalPrice.toPlainString()}")

        val sortedBundles = order.bundles.sortedByDescending { it.price }
        var currCount = 0
        sortedBundles.forEachIndexed { i, b ->
            if (i == sortedBundles.size - 1 ||
                sortedBundles[i + 1] != b) {
                println("    ${++currCount} x ${b.quantity} $${b.price.toPlainString()}")
                currCount = 0
            } else {
                currCount++
            }
        }
    }
}
