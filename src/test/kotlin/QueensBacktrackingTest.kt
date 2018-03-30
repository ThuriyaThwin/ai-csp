import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class QueensBacktrackingTest {

    @TestFactory
    fun findFirst() = listOf(
            1 to "[[true]]",
            2 to null,
            3 to null,
            4 to "[[false, false, true, false], [true, false, false, false], [false, false, false, true], [false, true, false, false]]",
            5 to "[[true, false, false, false, false], [false, false, false, true, false], [false, true, false, false, false], [false, false, false, false, true], [false, false, true, false, false]]")
            .map { (n, expected) ->
                DynamicTest.dynamicTest("findFirst() for QueenProblem($n) gives $expected") {
                    val problem = QueensProblem(n)
                    val backtrackingExecutor = BacktrackingExecutor(problem)
                    val result = backtrackingExecutor.findFirst()
                    assertThat(result).isEqualTo(expected)
                }
            }

    @TestFactory
    fun countAll() = listOf(
            1 to 1,
            2 to 0,
            3 to 0,
            4 to 2,
            5 to 10)
            .map { (n, expected) ->
                DynamicTest.dynamicTest("countAll() for QueenProblem($n) gives $expected") {
                    val problem = QueensProblem(n)
                    val backtrackingExecutor = BacktrackingExecutor(problem)
                    val result = backtrackingExecutor.countAll()
                    assertThat(result).isEqualTo(expected)
                }
            }
}
