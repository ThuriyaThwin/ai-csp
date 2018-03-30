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
            5 to 10,
            6 to 4,
            7 to 40,
            8 to 92,
            9 to 352,
            10 to 724)
//            11 to 2680,
//            12 to 14200,
//            13 to 73712)
            .map { (n, expected) ->
                DynamicTest.dynamicTest("countAll() for QueenProblem($n) gives $expected") {
                    val problem = QueensProblem(n)
                    val backtrackingExecutor = BacktrackingExecutor(problem)
                    val result = backtrackingExecutor.countAll()
                    assertThat(result).isEqualTo(expected)
                }
            }
}
