package model

typealias ProductCode = String

data class Product(
    val name: String,
    val code: ProductCode,
    val bundles: Set<Bundle>,
)
