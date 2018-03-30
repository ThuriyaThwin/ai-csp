import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class QueensProblemTest {

    @TestFactory
    fun testSquares() = listOf(
            1 to "[[true]]",
            2 to null,
            3 to null,
            4 to "[[false, false, true, false], [true, false, false, false], [false, false, false, true], [false, true, false, false]]",
            5 to "[[true, false, false, false, false], [false, false, false, true, false], [false, true, false, false, false], [false, false, false, false, true], [false, false, true, false, false]]")
            .map { (n, expected) ->
                DynamicTest.dynamicTest("resultExists($n) gives $expected") {
                    val problem = QueensProblem(n)
                    val backtrackingExecutor = BacktrackingExecutor(problem)
                    val result = backtrackingExecutor.findFirst()
                    assertThat(result).isEqualTo(expected)
                }
            }
}
