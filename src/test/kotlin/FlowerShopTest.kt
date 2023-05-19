import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.Test
import service.OrderPrinterService.IMPOSSIBLE_EXACT_SOLUTION_MESSAGE
import kotlin.test.assertEquals

class FlowerShopTest {

    @Test
    fun `Base example works`() {
        val output = tapSystemOut {
            main(arrayOf(
                "10 R12",
                "15 L09",
                "13 T58",
            ))
        }.also { println(it) }

        output.assertLines(
            "10 R12 $12.99",
            "1 x 10 $12.99",
            "15 L09 $41.90",
            "1 x 9 $24.95",
            "1 x 6 $16.95",
            "13 T58 $25.85",
            "2 x 5 $9.95",
            "1 x 3 $5.95",
        )
    }

    @Test
    fun `Other examples works`() {
        val output = tapSystemOut {
            main(arrayOf(
                "7 R12",
                "2 L09",
                "90 T58",
            ))
        }.also { println(it) }

        output.assertLines(
            IMPOSSIBLE_EXACT_SOLUTION_MESSAGE,
            "7 R12 $6.99",
            "1 x 5 $6.99",
            IMPOSSIBLE_EXACT_SOLUTION_MESSAGE,
            "2 L09 $0",
            "90 T58 $169.90",
            "10 x 9 $16.99",
        )
    }

    private fun String.assertLines(vararg lines: String) {
        this.trim().split('\n').forEachIndexed { i, o ->
            assertEquals(lines[i].trim(), o.trim())
        }
    }
}
