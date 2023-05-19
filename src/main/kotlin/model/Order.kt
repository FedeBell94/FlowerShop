package model

data class Order(
    val quantity: Int = 0,
    val bundles: List<Bundle> = emptyList(),
)
