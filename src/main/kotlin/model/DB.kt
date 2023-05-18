package model

import java.math.BigDecimal

object DB {

    fun getProductByCode(productCode: ProductCode): Product? =
        products.singleOrNull { it.code == productCode }

    private val products = setOf(
        Product(
            name = "Roses",
            code = "R12",
            bundles = setOf(
                Bundle(5, BigDecimal.valueOf(6.99)),
                Bundle(10, BigDecimal.valueOf(12.99))
            )
        ),
        Product(
            name = "Lilies",
            code = "L09",
            bundles = setOf(
                Bundle(3, BigDecimal.valueOf(9.95)),
                Bundle(6, BigDecimal.valueOf(16.95)),
                Bundle(9, BigDecimal.valueOf(24.95)),
            )
        ),
        Product(
            name = "Tulips",
            code = "T58",
            bundles = setOf(
                Bundle(3, BigDecimal.valueOf(5.95)),
                Bundle(5, BigDecimal.valueOf(9.95)),
                Bundle(9, BigDecimal.valueOf(16.99)),
            )
        ),
    )
}
