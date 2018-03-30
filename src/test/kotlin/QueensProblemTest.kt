import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory


internal class QueensProblemTest {

    private val problem = QueensProblem()

    @TestFactory
    fun testSquares() = listOf(
            1 to true,
            2 to false,
            3 to false,
            4 to true)
            .map { (n, expected) ->
                DynamicTest.dynamicTest("resultExists($n) gives $expected") {
                    val resultExists = problem.resultExists(n)
                    assertThat(resultExists).isEqualTo(expected)
                }
            }
}
