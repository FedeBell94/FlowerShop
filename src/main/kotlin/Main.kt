import model.DB
import service.FlowerShopService
import service.OrderPrinterService

fun main(args: Array<String>) {
    args.forEach { arg ->
        val (quantity, productCode) = arg.split(' ').let {
            if (it.size != 2) throw IllegalArgumentException("Expected list of '{quantity} {productCode}'")
            it.first().toInt() to it.last()
        }
        val product = DB.getProductByCode(productCode)
            ?: throw IllegalArgumentException("Product with code $productCode not found")

        FlowerShopService.findBestOrder(quantity, product.bundles)
            .also { OrderPrinterService.printFormatted(quantity, product, it) }
    }
}
