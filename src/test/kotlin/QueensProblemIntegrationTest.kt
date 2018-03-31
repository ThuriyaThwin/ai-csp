import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class QueensProblemIntegrationTest {

    private val firstResults = listOf(
            1 to "[1]",
            2 to null,
            3 to null,
            4 to "[2, 4, 1, 3]",
            5 to "[1, 3, 5, 2, 4]"
    )

    @TestFactory
    fun `findFirst() via backtracking`() = testFindFirst { BacktrackingExecutor(it) }

    @TestFactory
    fun `findFirst() via forward checking`() = testFindFirst { ForwardCheckingExecutor(it) }

    private fun testFindFirst(executorInitializer: (Problem) -> CspExecutor) =
            firstResults.map { (n, expected) ->
                DynamicTest.dynamicTest("for QueenProblem($n) gives $expected") {
                    val problem = QueensProblem(n)
                    val executor = executorInitializer(problem)
                    val result = executor.findFirst()
                    assertThat(result).isEqualTo(expected)
                }
            }

    private val allResultsCounts = listOf(
            1 to 1,
            2 to 0,
            3 to 0,
            4 to 2,
            5 to 10,
            6 to 4,
            7 to 40,
            8 to 92,
            9 to 352,
            10 to 724,
            11 to 2680
//            12 to 14200,
//            13 to 73712
    )

    @TestFactory
    fun `countAll() via backtracking`() = testCountAll { BacktrackingExecutor(it) }

    @TestFactory
    fun `countAll() via forward checking`() = testCountAll { ForwardCheckingExecutor(it) }

    private fun testCountAll(executorInitializer: (Problem) -> CspExecutor) =
            allResultsCounts.map { (n, expected) ->
                DynamicTest.dynamicTest("for QueenProblem($n) gives $expected") {
                    val problem = QueensProblem(n)
                    val executor = executorInitializer(problem)
                    val result = executor.countAll()
                    assertThat(result).isEqualTo(expected)
                }
            }
}
